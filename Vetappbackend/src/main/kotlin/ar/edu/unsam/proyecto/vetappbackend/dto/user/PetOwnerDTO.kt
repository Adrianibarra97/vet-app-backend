package ar.edu.unsam.proyecto.vetappbackend.dto.user
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*

data class PetOwnerDTO (
    var id: Int,
    var name: String,
    var surname: String,
    var dni: Int,
    var email: String,
    var telephone: String,
    var username: String,
    var landline: String,
    var address: String,
    var idUserData: Int,
    val photo: String

    )

fun PetOwner.toDTO(): PetOwnerDTO {
    return PetOwnerDTO(
        id = this.id!!,
        name = this.name,
        surname = this.surname,
        dni = this.dni,
        email = this.email,
        telephone = this.telephone,
        address = this.address,
        idUserData = this.userData.id!!,
        username = this.username,
        landline = this.landline,
        photo =  this.photo
    )
}

fun PetOwnerDTO.fromJSON(userDataCurrent: UserData): PetOwner {
    val petOwnerDTO = this
    return PetOwner().apply {
        id = petOwnerDTO.id
        name = petOwnerDTO.name
        surname = petOwnerDTO.surname
        dni = petOwnerDTO.dni
        email = petOwnerDTO.email
        telephone = petOwnerDTO.telephone
        address = petOwnerDTO.address
        userData = userDataCurrent
        landline = petOwnerDTO.landline
        username = petOwnerDTO.username
        photo = petOwnerDTO.photo
    }
}
