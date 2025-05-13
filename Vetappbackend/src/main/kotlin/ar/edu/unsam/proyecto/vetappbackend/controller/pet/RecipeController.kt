package ar.edu.unsam.proyecto.vetappbackend.controller.pet

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.Pet
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.Recipe
import ar.edu.unsam.proyecto.vetappbackend.domain.user.Vet
import ar.edu.unsam.proyecto.vetappbackend.dto.pet.RecipeRequestDTO
import ar.edu.unsam.proyecto.vetappbackend.dto.pet.RecipeResponseDTO
import ar.edu.unsam.proyecto.vetappbackend.dto.pet.fromJSON
import ar.edu.unsam.proyecto.vetappbackend.dto.pet.toDTO
import ar.edu.unsam.proyecto.vetappbackend.service.pet.PetService
import ar.edu.unsam.proyecto.vetappbackend.service.pet.RecipeService
import ar.edu.unsam.proyecto.vetappbackend.service.user.VetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin("*")
@RequestMapping("/recipe")
class RecipeController {

    @Autowired
    private lateinit var recipeService: RecipeService

    @Autowired
    private lateinit var petService: PetService

    @Autowired
    private lateinit var vetService: VetService

    @GetMapping("/get-all")
    fun getAll(): List<RecipeResponseDTO> {
        return this.recipeService.getAll().map { it.toDTO() }
    }

    @GetMapping("/get-one-by-id")
    fun getOneById(@RequestParam idRecipe: Int): RecipeResponseDTO {
        return this.recipeService.getOneById(idRecipe).toDTO()
    }

    @DeleteMapping("/delete")
    fun delete(@RequestParam idRecipe: Int) {
        val recipeForDelete: Recipe = this.recipeService.getOneById(idRecipe)
        this.recipeService.delete(recipeForDelete)
    }

    @PostMapping("/create")
    fun create(@RequestBody recipeRequestDTO: RecipeRequestDTO, @RequestParam idPet: Int, @RequestParam idVet: Int) {
        val pet: Pet = this.petService.getOneById(idPet)
        val vet: Vet = this.vetService.getOneById(idVet)
        val recipe: Recipe = recipeRequestDTO.fromJSON(pet, vet)
        this.recipeService.create(recipe)
    }

    @PutMapping("/update")
    fun update(@RequestBody recipeRequestDTO: RecipeRequestDTO, @RequestParam idPet: Int, @RequestParam idVet: Int) {
        val pet: Pet = this.petService.getOneById(idPet)
        val vet: Vet = this.vetService.getOneById(idVet)
        val recipe: Recipe = recipeRequestDTO.fromJSON(pet, vet)
        recipeService.update(recipe)
    }

}