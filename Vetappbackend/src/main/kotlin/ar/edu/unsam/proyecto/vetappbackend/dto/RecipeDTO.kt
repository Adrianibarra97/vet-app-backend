package ar.edu.unsam.proyecto.vetappbackend.dto

import ar.edu.unsam.proyecto.vetappbackend.domain.shift.MedicalShift
import ar.edu.unsam.proyecto.vetappbackend.domain.shift.Recipe

data class RecipeDTO (
    val id : Int,
    val description : String,
    val medicalShiftId : Int
)

fun Recipe.toDTO(): RecipeDTO {
    return RecipeDTO(
        id = this.id!!,
        description = this.description,
        medicalShiftId = this.medicalShift?.id!!
    )
}

fun RecipeDTO.fromJSON(recipeDTO: RecipeDTO, medicalShift: MedicalShift): Recipe {
    val recipe = Recipe()
    recipe.id = recipeDTO.id
    recipe.description = recipeDTO.description
    recipe.medicalShift = medicalShift

    return recipe
}