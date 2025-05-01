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
        observation = this.observation!!,
        diagnosisDate = this.diagnosisDate.toString(),
        severity = this.severity!!.name,
        type = this.type!!.name

    )
}

fun PreExistenceDiseaseDTO.fromJSON(preExistenceDiseaseDTO: PreExistenceDiseaseDTO, medicalHistory: MedicalHistory): PreExistenceDisease {
    val preExistenceDisease = PreExistenceDisease()
    preExistenceDisease.id = preExistenceDiseaseDTO.id
    preExistenceDisease.medicalHistory = medicalHistory
    preExistenceDisease.isActive = preExistenceDiseaseDTO.isActive
    preExistenceDisease.observation = preExistenceDiseaseDTO.observation
    preExistenceDisease.diagnosisDate = preExistenceDiseaseDTO.diagnosisDate.let { LocalDate.parse(it) }
    preExistenceDisease.severity = TypeOfSeverity.valueOf(preExistenceDiseaseDTO.severity)
    preExistenceDisease.type = TypeOfPreExistinceDisease.valueOf(preExistenceDiseaseDTO.type)
    return preExistenceDisease
}