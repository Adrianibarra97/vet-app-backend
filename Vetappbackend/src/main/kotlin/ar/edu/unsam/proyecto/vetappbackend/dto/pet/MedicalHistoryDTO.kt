package ar.edu.unsam.proyecto.vetappbackend.dto.pet

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.MedicalHistory
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.Pet
import java.time.LocalDate

data class MedicalHistoryDTO(
    val id: Int,
    val idPet: Int,
    val summary: String,
    val createdAt: String,
    val updatedAt: String
)

fun MedicalHistory.toDTO(): MedicalHistoryDTO {
    return MedicalHistoryDTO(
        id = this.id!!,
        idPet = this.pet?.id!!,
        summary = this.summary,
        createdAt = this.createdAt?.toString() ?: throw IllegalArgumentException("La fecha de creación no puede ser nula"),
        updatedAt = this.updatedAt?.toString() ?: throw IllegalArgumentException("La fecha de actualización no puede ser nula")
    )
}

fun MedicalHistoryDTO.fromJSON(petCurrent: Pet): MedicalHistory {
    val medicalHistoryDTO = this
    return MedicalHistory().apply {
        id = medicalHistoryDTO.id
        pet = petCurrent
        summary = medicalHistoryDTO.summary
        createdAt = medicalHistoryDTO.createdAt.let { LocalDate.parse(it) }
        updatedAt = medicalHistoryDTO.updatedAt.let { LocalDate.parse(it) }
       
    }
}
