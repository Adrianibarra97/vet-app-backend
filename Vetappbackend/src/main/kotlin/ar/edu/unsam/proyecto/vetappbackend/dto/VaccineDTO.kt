package ar.edu.unsam.proyecto.vetappbackend.dto

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

fun VaccineDTO.fromJSON(vaccineDTO: VaccineDTO, medicalHistory: MedicalHistory): Vaccine {
    val vaccine = Vaccine()
    vaccine.id = vaccineDTO.id
    vaccine.medicalHistory = medicalHistory
    vaccine.applicationDate = vaccineDTO.applicationDate.let { LocalDate.parse(it) }
    vaccine.expirationDate = vaccineDTO.expirationDate.let { LocalDate.parse(it) }
    vaccine.description = vaccineDTO.description
    vaccine.batchNumber = vaccineDTO.batchNumber
    vaccine.completed = vaccineDTO.completed
    vaccine.type = TypeOfVaccine.valueOf(vaccineDTO.type)

    return vaccine
}