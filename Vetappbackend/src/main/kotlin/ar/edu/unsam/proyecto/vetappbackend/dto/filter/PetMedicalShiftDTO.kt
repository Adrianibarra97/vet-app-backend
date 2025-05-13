package ar.edu.unsam.proyecto.vetappbackend.dto.filter
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*

data class PetMedicalShiftDTO(val id: Int, val name: String )

fun Pet.toPetMedicalShiftDTO(): PetMedicalShiftDTO {
    return PetMedicalShiftDTO(id = this.id!!, name = this.name!!)
}