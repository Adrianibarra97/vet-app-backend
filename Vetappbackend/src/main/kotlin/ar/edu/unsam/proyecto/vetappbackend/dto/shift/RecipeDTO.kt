package ar.edu.unsam.proyecto.vetappbackend.dto.shift
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.MedicalHistory
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
    val medicalHistoryId : Int
)

fun Recipe.toResponseDTO(): RecipeResponseDTO {

    return RecipeResponseDTO(
        id = this.id!!,
        description = this.description,
        dateRecipe = this.date.toString(),
        nameVet = this.vet?.name!!
    )
}

fun Recipe.toDTO(): RecipeDTO {
    return RecipeDTO(
        id = this.id!!,
        description = this.description,
        medicalHistoryId = this.medicalHistory?.id!!
    )
}

fun RecipeDTO.fromJSON(medicalHistoryCurrent: MedicalHistory): Recipe {
    val recipeDTO = this
    return Recipe().apply {
        id = recipeDTO.id
        description = recipeDTO.description
        medicalHistory = medicalHistoryCurrent
    }

}