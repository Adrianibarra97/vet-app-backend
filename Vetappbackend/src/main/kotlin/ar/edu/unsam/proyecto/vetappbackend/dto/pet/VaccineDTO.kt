package ar.edu.unsam.proyecto.vetappbackend.dto.pet

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import java.time.LocalDate

data class VaccineDTO(
    val id: Int,
    val medicalHistoryId: Int,
    val applicationDate: String,
    val expirationDate: String,
    val description: String,
    val batchNumber: Int,
    val completed: Boolean,
    val type: String
)

fun Vaccine.toDTO(): VaccineDTO {
    return VaccineDTO(
        id = this.id!!,
        medicalHistoryId = this.medicalHistory?.id!!,
        applicationDate = this.applicationDate.toString(),
        expirationDate = this.expirationDate.toString(),
        description = this.description,
        batchNumber = this.batchNumber,
        completed = this.completed,
        type = this.type!!.name
    )
}

fun VaccineDTO.fromJSON(medicalHistory: MedicalHistory): Vaccine {
    return Vaccine().apply {
        this.id = id
        this.medicalHistory = medicalHistory
        this.applicationDate = LocalDate.parse(applicationDate.toString())
        this.expirationDate = LocalDate.parse(expirationDate.toString())
        this.description = description
        this.batchNumber = batchNumber
        this.completed = completed
        this.type = TypeOfVaccine.valueOf(type.toString())
    }
}