package ar.edu.unsam.proyecto.vetappbackend.controller.pet

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import ar.edu.unsam.proyecto.vetappbackend.dto.pet.RecipeDTO
import ar.edu.unsam.proyecto.vetappbackend.dto.pet.fromJSON
import ar.edu.unsam.proyecto.vetappbackend.dto.pet.toDTO
import ar.edu.unsam.proyecto.vetappbackend.service.pet.*
import ar.edu.unsam.proyecto.vetappbackend.service.user.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
@RequestMapping("/recipe")
class RecipeController {

    @Autowired
    private lateinit var recipeService: RecipeService

    @Autowired
    private lateinit var medicalHistoryService: MedicalHistoryService

    @Autowired
    private lateinit var vetService: VetService

    @GetMapping("/get-all")
    fun getAll(): List<RecipeDTO> {
        return this.recipeService.getAll().map { it.toDTO() }
    }

    @GetMapping("/get-one-by-id")
    fun getOneById(@RequestParam idRecipe: Int): RecipeDTO {
        return this.recipeService.getOneById(idRecipe).toDTO()
    }

    @PostMapping("/create")
    fun create(@RequestBody newRecipeDTO: RecipeDTO) {
        val medicalHistory: MedicalHistory = medicalHistoryService.getOneById(newRecipeDTO.medicalHistoryId)
        val vet: Vet = this.vetService.getOneById(newRecipeDTO.vetId)
        this.recipeService.create(newRecipeDTO.fromJSON(medicalHistory,vet))
    }

    @PutMapping("/update")
    fun update(@RequestBody newRecipeDTO: RecipeDTO) {
        val medicalHistory: MedicalHistory = medicalHistoryService.getOneById(newRecipeDTO.medicalHistoryId)
        val vet: Vet = this.vetService.getOneById(newRecipeDTO.vetId)
        val recipe: Recipe = newRecipeDTO.fromJSON(medicalHistory, vet)
        recipeService.update(recipe)
    }

    @DeleteMapping("/delete")
    fun delete(@RequestParam idRecipe: Int) {
        val recipeForDelete: Recipe = this.recipeService.getOneById(idRecipe)
        this.recipeService.delete(recipeForDelete)
    }

}