package ar.edu.unsam.proyecto.vetappbackend.dto

import ar.edu.unsam.proyecto.vetappbackend.domain.PetFilter
import java.time.LocalDate

data class PetFilterDTO(
    val name: String?,
    val shiftDate: String?,
    val pendingVaccine: Boolean?
)


fun PetFilter.toJSON(): PetFilterDTO {
    return PetFilterDTO(
        name = this.name.toString(),
        shiftDate = this.shiftDate.toString(),
        pendingVaccine = this.pendingVaccine
    )
}

fun PetFilterDTO.fromJSON(petFilterDTO: PetFilterDTO): PetFilter {

    val petFilter = PetFilter()
    petFilter.name = petFilterDTO.name.toString()
    petFilter.shiftDate = petFilterDTO.shiftDate?.let { LocalDate.parse(it) }
    petFilter.pendingVaccine = petFilterDTO.pendingVaccine

    return petFilter
}