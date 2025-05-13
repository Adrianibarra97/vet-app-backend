package ar.edu.unsam.proyecto.vetappbackend.dto.pet
import java.time.LocalDate
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.domain.type.*

data class PreExistenceDiseaseDTO (
    val id: Int,
    val isActive: Boolean,
    val observation: String,
    val diagnosisDate: String,
    val severity: String,
    val type: String
)

fun PreExistenceDisease.toDTO(): PreExistenceDiseaseDTO {
    return PreExistenceDiseaseDTO(
        id = this.id!!,
        isActive = this.isActive,
        observation = this.observation!!,
        diagnosisDate = this.diagnosisDate.toString(),
        severity = this.severity!!.name,
        type = this.type!!.name
    )
}

fun PreExistenceDiseaseDTO.fromJSON(currentPet: Pet): PreExistenceDisease {
    val preExistenceDiseaseDTO = this
    return PreExistenceDisease().apply {
        id = preExistenceDiseaseDTO.id
        isActive = preExistenceDiseaseDTO.isActive
        observation = preExistenceDiseaseDTO.observation
        diagnosisDate = LocalDate.parse(preExistenceDiseaseDTO.diagnosisDate.toString())
        severity = TypeOfSeverity.valueOf(preExistenceDiseaseDTO.severity.toString())
        pet = currentPet
    }
}