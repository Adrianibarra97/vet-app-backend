package ar.edu.unsam.proyecto.vetappbackend.dto.pet
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import java.time.LocalDate

data class RecipeRequestDTO (
    val id : Int,
    val date: String,
    val description : String,
)

data class RecipeResponseDTO (
    val id: Int,
    val date: String,
    val description: String,
    val namePet: String,
    val nameVet: String
)


fun Recipe.toDTO(): RecipeResponseDTO {
    return RecipeResponseDTO(
        id = this.id!!,
        date = this.date.toString(),
        description = this.description!!,
        namePet = this.pet?.name!!,
        nameVet = this.vet?.name!!
    )
}

fun RecipeRequestDTO.fromJSON(currentPet: Pet, currentVet: Vet): Recipe {
    val recipeDTO = this
    return Recipe().apply {
        id = recipeDTO.id
        description = recipeDTO.description
        date = recipeDTO.date.let { LocalDate.parse(it) }
        pet = currentPet
        vet = currentVet
    }
}