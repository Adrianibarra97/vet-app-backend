package ar.edu.unsam.proyecto.vetappbackend.dto.user
import java.time.*
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*

data class MedicalShiftDTO(
    val id: Int,
    val date: String,
    val hour: String,
)

data class MedicalShiftResponseDTO(
    val id: Int,
    val idPet: Int,
    val date: String,
    val hour: String,
    val nameVet: String,
    val namePet: String
)

fun MedicalShift.toDTO(): MedicalShiftResponseDTO {
    return MedicalShiftResponseDTO(
        id = this.id!!,
        idPet = this.pet?.id!!,
        date = this.date.toString(),
        hour = this.hour.toString(),
        nameVet = this.vet?.name!!,
        namePet = this.pet?.name!!
    )
}

fun MedicalShiftDTO.fromJSON(petCurrent: Pet, vetCurrent: Vet): MedicalShift {
    val medicalShiftDTO = this
    return MedicalShift().apply {
        id = medicalShiftDTO.id
        date = medicalShiftDTO.date.let { LocalDate.parse(it) }
        hour = medicalShiftDTO.hour.let { LocalTime.parse(it) }
        pet = petCurrent
        vet = vetCurrent
    }
}