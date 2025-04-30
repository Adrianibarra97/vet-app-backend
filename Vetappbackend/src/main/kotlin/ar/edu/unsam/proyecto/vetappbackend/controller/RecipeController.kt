package ar.edu.unsam.proyecto.vetappbackend.controller
import ar.edu.unsam.proyecto.vetappbackend.domain.shift.MedicalShift
import ar.edu.unsam.proyecto.vetappbackend.domain.shift.Recipe
import ar.edu.unsam.proyecto.vetappbackend.dto.RecipeDTO
import ar.edu.unsam.proyecto.vetappbackend.dto.fromJSON
import ar.edu.unsam.proyecto.vetappbackend.dto.toDTO
import ar.edu.unsam.proyecto.vetappbackend.service.MedicalShiftService
import ar.edu.unsam.proyecto.vetappbackend.service.RecipeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
@RequestMapping("/recipe")
class RecipeController {

    @Autowired
    private lateinit var recipeService: RecipeService

    @Autowired
    private lateinit var medicalShiftService: MedicalShiftService

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
        this.recipeService.create(newRecipeDTO.fromJSON(newRecipeDTO,medicalShift))
    }

    @PutMapping("/update")
    fun update(@RequestBody newRecipeDTO:RecipeDTO) {
        val medicalShift: MedicalShift = medicalShiftService.getOneById(newRecipeDTO.medicalShiftId)
        val recipe: Recipe = newRecipeDTO.fromJSON(newRecipeDTO,medicalShift)
        recipeService.update(recipe)
    }

    @DeleteMapping("/delete")
    fun delete(@RequestParam idRecipe: Int) {
        val recipeForDelete: Recipe = this.recipeService.getOneById(idRecipe)
        this.recipeService.delete(recipeForDelete)
    }

}