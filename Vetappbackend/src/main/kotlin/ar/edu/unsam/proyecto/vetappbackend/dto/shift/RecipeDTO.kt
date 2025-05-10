package ar.edu.unsam.proyecto.vetappbackend.dto.shift
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.MedicalHistory
import ar.edu.unsam.proyecto.vetappbackend.domain.shift.*
import ar.edu.unsam.proyecto.vetappbackend.domain.user.Vet
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class RecipeResponseDTO (
    val id: Int,
    val description: String,
    val dateRecipe: String,
    val nameVet: String
)

data class RecipeDTO (
    val id : Int,
    val description : String,
    val medicalHistoryId : Int,
    val vetId : Int,
    val date: String
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
        medicalHistoryId = this.medicalHistory?.id!!,
        vetId = this.vet?.id!!,
        date = this.date.toString()
    )
}

fun RecipeDTO.fromJSON(medicalHistoryCurrent: MedicalHistory, currentVet: Vet): Recipe {
    val recipeDTO = this
    return Recipe().apply {
        id = recipeDTO.id
        description = recipeDTO.description
        medicalHistory = medicalHistoryCurrent
        vet = currentVet
        date = recipeDTO.date.let { LocalDate.parse(it) }
    }

}