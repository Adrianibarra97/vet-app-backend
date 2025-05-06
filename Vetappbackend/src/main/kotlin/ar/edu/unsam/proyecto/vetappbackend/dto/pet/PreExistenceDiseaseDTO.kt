package ar.edu.unsam.proyecto.vetappbackend.dto.pet
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import java.time.LocalDate

data class PreExistenceDiseaseDTO (
    val id: Int,
    val medicalHistoryId: Int,
    val isActive: Boolean,
    val observation: String,
    val diagnosisDate: String,
    val severity: String,
    val type: String
)

fun PreExistenceDisease.toDTO(): PreExistenceDiseaseDTO {
    return PreExistenceDiseaseDTO(
        id = this.id!!,
        medicalHistoryId = this.medicalHistory?.id!!,
        isActive = this.isActive,
        observation = this.observation,
        diagnosisDate = this.diagnosisDate.toString(),
        severity = this.severity!!.name,
        type = this.type!!.name
    )
}

fun PreExistenceDiseaseDTO.fromJSON(medicalHistoryCurrent: MedicalHistory): PreExistenceDisease {
    val preExistenceDiseaseDTO = this
    return PreExistenceDisease().apply {
        medicalHistory = medicalHistoryCurrent
        id = preExistenceDiseaseDTO.id
        isActive = preExistenceDiseaseDTO.isActive
        observation = preExistenceDiseaseDTO.observation
        diagnosisDate = LocalDate.parse(preExistenceDiseaseDTO.diagnosisDate.toString())
        severity = TypeOfSeverity.valueOf(preExistenceDiseaseDTO.severity.toString())
        type = TypeOfPreExistinceDisease.valueOf(preExistenceDiseaseDTO.type.toString())
    }
}