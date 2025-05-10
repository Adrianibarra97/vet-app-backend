package ar.edu.unsam.proyecto.vetappbackend.dto.shift
import ar.edu.unsam.proyecto.vetappbackend.domain.shift.*

data class RecipeResponseDTO (
    val id: Int,
    val description: String,
    val dateRecipe: String,
    val nameVet: String
)

data class RecipeDTO (
    val id : Int,
    val description : String,
    val medicalShiftId : Int
)

fun Recipe.toResponseDTO(): RecipeResponseDTO {
    return RecipeResponseDTO(
        id = this.id!!,
        description = this.description,
        dateRecipe = this.medicalShift?.date.toString(),
        nameVet = this.medicalShift?.vet?.name!!
    )
}

fun Recipe.toDTO(): RecipeDTO {
    return RecipeDTO(
        id = this.id!!,
        description = this.description,
        medicalShiftId = this.medicalShift?.id!!
    )
}

fun RecipeDTO.fromJSON(medicalShiftCurrent: MedicalShift): Recipe {
    val recipeDTO = this
    return Recipe().apply {
        id = recipeDTO.id
        description = recipeDTO.description
        medicalShift = medicalShiftCurrent
    }

}