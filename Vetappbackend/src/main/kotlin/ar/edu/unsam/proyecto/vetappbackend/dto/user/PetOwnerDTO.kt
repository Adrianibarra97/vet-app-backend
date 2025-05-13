package ar.edu.unsam.proyecto.vetappbackend.dto.user
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*

data class PetOwnerDTO (
    val id: Int,
    val dni: Int,
    val name: String,
    val surname: String,
    val photo: String,
    val email: String,
    val telephone: String,
    val emergencyContactName: String,
    val emergencyContactPhone: String
)

fun PetOwner.toDTO(): PetOwnerDTO {
    return PetOwnerDTO(
        id = this.id,
        dni = this.dni!!,
        name = this.name!!,
        surname = this.surname!!,
        photo = this.photo!!,
        email = this.email!!,
        telephone = this.telephone!!,
        emergencyContactName = this.emergencyContactName!!,
        emergencyContactPhone = this.emergencyContactPhone!!
    )
}

fun PetOwnerDTO.fromJSON(): PetOwner {
    val petOwnerDTO = this
    return PetOwner().apply {
        id = petOwnerDTO.id
        dni = petOwnerDTO.dni
        name = petOwnerDTO.name
        surname = petOwnerDTO.surname
        photo = petOwnerDTO.photo
        email = petOwnerDTO.email
        telephone = petOwnerDTO.telephone
        emergencyContactName = petOwnerDTO.emergencyContactName
        emergencyContactPhone = petOwnerDTO.emergencyContactPhone
    }
}