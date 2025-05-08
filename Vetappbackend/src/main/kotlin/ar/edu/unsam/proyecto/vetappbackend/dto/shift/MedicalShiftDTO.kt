package ar.edu.unsam.proyecto.vetappbackend.dto.shift
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.domain.shift.*
import ar.edu.unsam.proyecto.vetappbackend.dto.pet.PetMedicalShiftDTO
import ar.edu.unsam.proyecto.vetappbackend.dto.pet.toPetMedicalShiftDTO
import java.time.*
import kotlin.*

//CREATE-UPDATE-GET
data class MedicalShiftRequestDTO(
    val id: Int,
    val date: String,
    val hour: String,
    val vetId: Int,
    val petId: Int
)

// DOMAIN -> DTO
data class MedicalShiftResponseDTO(
    val id: Int,
    val date: String,
    val hour: String,
    val nameVet: String,
    val petMedicalShift: PetMedicalShiftDTO
)

fun MedicalShift.toDTO(): MedicalShiftResponseDTO {
    return MedicalShiftResponseDTO(
        id = this.id!!,
        date = this.date?.toString() ?: throw IllegalArgumentException("La fecha del turno no puede ser nula"),
        hour = this.hour?.toString() ?: throw IllegalArgumentException("La hora del turno no puede ser nula"),
        nameVet = this.vet!!.name!!,
        petMedicalShift = this.pet!!.toPetMedicalShiftDTO()
    )
}

fun MedicalShiftRequestDTO.fromJSON(vetCurrent: Vet, petCurrent: Pet): MedicalShift {
    val medicalShiftDTO = this
    return MedicalShift().apply {
        id = medicalShiftDTO.id
        date = medicalShiftDTO.date.let { LocalDate.parse(it) }
        hour = medicalShiftDTO.hour.let { LocalTime.parse(it) }
        vet = vetCurrent
        pet = petCurrent
    }
}