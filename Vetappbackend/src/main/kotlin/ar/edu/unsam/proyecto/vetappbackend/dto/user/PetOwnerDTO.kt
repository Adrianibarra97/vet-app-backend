package ar.edu.unsam.proyecto.vetappbackend.dto.user
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*

data class PetOwnerDTO (
    var id: Int?,
    var name: String,
    var surname: String,
    var dni: Int,
    var email: String,
    var telephone: String,
    var address: String,

)

fun PetOwner.toDTO(): PetOwnerDTO {
    return PetOwnerDTO(
        id = this.id!!,
        name = this.name,
        surname = this.surname,
        dni = this.dni,
        email = this.email,
        telephone = this.telephone,
        address = this.address
    )
}

fun PetOwnerDTO.fromJSON(petOwnerDTO: PetOwnerDTO): PetOwner {
    val petOwner = PetOwner()
    petOwner.id = petOwnerDTO.id
    petOwner.name = petOwnerDTO.name
    petOwner.surname = petOwnerDTO.surname
    petOwner.dni = petOwnerDTO.dni
    petOwner.email = petOwnerDTO.email
    petOwner.telephone = petOwnerDTO.telephone
    petOwner.address = petOwnerDTO.address
    return petOwner
}