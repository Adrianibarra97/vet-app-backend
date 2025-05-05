package ar.edu.unsam.proyecto.vetappbackend.dto.pet

import java.time.LocalDate

data class FilterPet (
    val name: String? = null,
    val hasMedicalShift: Boolean? = null,
    val hasPendingVaccine: Boolean? = null
)

data class FilterPetDTO (
    val name: String?,
    val hasMedicalShift: Boolean?,
    val hasPendingVaccine: Boolean?
)

fun FilterPetDTO.fromJSON(): FilterPet {
    val filterPetDTO = this
    return FilterPet(
        name = filterPetDTO.name?.takeIf { it.isNotBlank() },
        hasMedicalShift = filterPetDTO.hasMedicalShift,
        hasPendingVaccine = filterPetDTO.hasPendingVaccine
    )
}