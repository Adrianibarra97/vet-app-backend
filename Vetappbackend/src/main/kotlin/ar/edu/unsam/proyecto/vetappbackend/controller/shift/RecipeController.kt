package ar.edu.unsam.proyecto.vetappbackend.controller.shift
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.*
import ar.edu.unsam.proyecto.vetappbackend.dto.shift.*
import ar.edu.unsam.proyecto.vetappbackend.domain.shift.*
import ar.edu.unsam.proyecto.vetappbackend.service.shift.*

@RestController
@CrossOrigin("*")
@RequestMapping("/recipe")
class RecipeController {

    @Autowired private lateinit var recipeService: RecipeService

    @Autowired private lateinit var medicalShiftService: MedicalShiftService

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
        val medicalShift: MedicalShift = medicalShiftService.getOneById(newRecipeDTO.medicalShiftId)
        this.recipeService.create(newRecipeDTO.fromJSON(medicalShift))
    }

    @PutMapping("/update")
    fun update(@RequestBody newRecipeDTO: RecipeDTO) {
        val medicalShift: MedicalShift = medicalShiftService.getOneById(newRecipeDTO.medicalShiftId)
        val recipe: Recipe = newRecipeDTO.fromJSON(medicalShift)
        recipeService.update(recipe)
    }

    @DeleteMapping("/delete")
    fun delete(@RequestParam idRecipe: Int) {
        val recipeForDelete: Recipe = this.recipeService.getOneById(idRecipe)
        this.recipeService.delete(recipeForDelete)
    }

}