package ar.edu.unsam.proyecto.vetappbackend.dto.pet

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.domain.type.*
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

fun VaccineDTO.fromJSON(medicalHistoryCurrnet: MedicalHistory): Vaccine {
    val vaccineDTO = this
    return Vaccine().apply {
        id = vaccineDTO.id
        medicalHistory = medicalHistoryCurrnet
        applicationDate = vaccineDTO.applicationDate.let { LocalDate.parse(it) }
        expirationDate = vaccineDTO.expirationDate.let { LocalDate.parse(it) }
        description = vaccineDTO.description
        batchNumber = vaccineDTO.batchNumber
        completed = vaccineDTO.completed
        type = TypeOfVaccinePet.valueOf(vaccineDTO.type)
    }

}