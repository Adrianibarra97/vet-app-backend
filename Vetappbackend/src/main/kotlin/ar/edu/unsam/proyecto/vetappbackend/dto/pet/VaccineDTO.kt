package ar.edu.unsam.proyecto.vetappbackend.dto.pet
import java.time.LocalDate
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.domain.type.*

data class VaccineDTO(
    val id: Int,
    val applicationDate: String,
    val expirationDate: String,
    val description: String,
    val batchNumber: Int,
    val completed: Boolean,
    val type: String
)

fun Vaccine.toDTO(): VaccineDTO {
    return VaccineDTO(
        id = this.id,
        applicationDate = this.applicationDate.toString(),
        expirationDate = this.expirationDate.toString(),
        description = this.description!!,
        batchNumber = this.batchNumber!!,
        completed = this.completed,
        type = this.type!!.name
    )
}

fun VaccineDTO.fromJSON(currentPet: Pet): Vaccine {
    val vaccineDTO = this
    return Vaccine().apply {
        id = vaccineDTO.id
        applicationDate = LocalDate.parse(vaccineDTO.applicationDate.toString())
        expirationDate = LocalDate.parse(vaccineDTO.expirationDate.toString())
        description = vaccineDTO.description
        batchNumber = vaccineDTO.batchNumber
        completed = vaccineDTO.completed
        type =TypeOfVaccine.valueOf(vaccineDTO.type)
        pet = currentPet
    }
}