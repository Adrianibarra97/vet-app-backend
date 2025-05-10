package ar.edu.unsam.proyecto.vetappbackend.controller.shift
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.MedicalHistory
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.*
import ar.edu.unsam.proyecto.vetappbackend.dto.shift.*
import ar.edu.unsam.proyecto.vetappbackend.domain.shift.*
import ar.edu.unsam.proyecto.vetappbackend.domain.user.Vet
import ar.edu.unsam.proyecto.vetappbackend.service.pet.MedicalHistoryService
import ar.edu.unsam.proyecto.vetappbackend.service.shift.*
import ar.edu.unsam.proyecto.vetappbackend.service.user.VetService

@RestController
@CrossOrigin("*")
@RequestMapping("/recipe")
class RecipeController {

    @Autowired private lateinit var recipeService: RecipeService

    @Autowired private lateinit var medicalHistoryService: MedicalHistoryService

    @Autowired private lateinit var vetService: VetService

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