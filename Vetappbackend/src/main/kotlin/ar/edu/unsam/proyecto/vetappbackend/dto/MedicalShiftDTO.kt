package ar.edu.unsam.proyecto.vetappbackend.dto

import ar.edu.unsam.proyecto.vetappbackend.domain.*
import java.time.LocalDate
import java.time.LocalTime
import kotlin.Int
import kotlin.toString

// Este es para mostrarlo
data class MedicalShiftResponseDTO(
    val id: Int,
    val date: String,
    val hour: String,
    val nameVet: String,
    val namePatient: String
)

fun MedicalShift.toJSON(): MedicalShiftResponseDTO {
    return MedicalShiftResponseDTO(
        id = this.id!!,
        date = this.date.toString(),
        hour = this.hour.toString(),
        nameVet = this.vet!!.name,
        namePatient = this.patient!!.name
    )
}


//UPDATE - CREATE -> IDs busca el objeto vet o pet, los convierte en Domain
data class MedicalShiftRequestDTO(
    val id: Int,
    val date: String,
    val hour: String,
    val vetId: Int,
    val patientId: Int
)

fun MedicalShiftRequestDTO.fromJSON(vet: Vet, patient: Pet, medicalShiftDTO: MedicalShiftRequestDTO): MedicalShift {
    val medicalShift = MedicalShift()
    medicalShift.id = medicalShiftDTO.id
    medicalShift.date = medicalShiftDTO.date.let { LocalDate.parse(it) }
    medicalShift.hour = medicalShiftDTO.hour.let { LocalTime.parse(it) }
    medicalShift.vet = vet
    medicalShift.patient = patient
    return medicalShift
}