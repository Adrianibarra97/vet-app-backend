package ar.edu.unsam.proyecto.vetappbackend.dto.pet

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.MedicalHistory
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.Pet
import java.time.LocalDate

data class MedicalHistoryDTO(
    val id: Int,
    val summary: String,
    val createdAt: String,
    val updatedAt: String
)

fun MedicalHistory.toDTO(): MedicalHistoryDTO {
    return MedicalHistoryDTO(
        id = this.id!!,
        summary = this.summary,
        createdAt = this.createdAt?.toString() ?: throw IllegalArgumentException("La fecha de creación no puede ser nula"),
        updatedAt = this.updatedAt?.toString() ?: throw IllegalArgumentException("La fecha de actualización no puede ser nula")
    )
}

fun MedicalHistoryDTO.fromJSON(): MedicalHistory {
    val medicalHistoryDTO = this
    return MedicalHistory().apply {
        id = medicalHistoryDTO.id
        summary = medicalHistoryDTO.summary
        createdAt = medicalHistoryDTO.createdAt.let { LocalDate.parse(it) }
        updatedAt = medicalHistoryDTO.updatedAt.let { LocalDate.parse(it) }
       
    }
}
