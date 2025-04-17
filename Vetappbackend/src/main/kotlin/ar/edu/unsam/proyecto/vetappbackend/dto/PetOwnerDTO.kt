package ar.edu.unsam.proyecto.vetappbackend.dto

import ar.edu.unsam.proyecto.vetappbackend.domain.PetOwner

data class PetOwnerDTO (
    var id: Int?,
    var name: String,
    var surname: String,
    var username: String,
    var password: String,
    var dni: Int,
    var email: String,
    var telephone: String,
    var address: String,

)

fun PetOwner.toJSON(): PetOwnerDTO {
    return PetOwnerDTO(
        id = this.id!!,
        name = this.name,
        surname = this.surname,
        username = this.username,
        password = this.password,
        dni = this.dni,
        email = this.email,
        telephone = this.telephone,
        address = this.address,
    )
}

fun PetOwnerDTO.fromJSON(petOwnerDTO: PetOwnerDTO): PetOwner {
    val petOwner = PetOwner()
    petOwner.id = petOwnerDTO.id
    petOwner.name = petOwnerDTO.name
    petOwner.surname = petOwnerDTO.surname
    petOwner.username = petOwnerDTO.username
    petOwner.password = petOwnerDTO.password
    petOwner.dni = petOwnerDTO.dni
    petOwner.email = petOwnerDTO.email
    petOwner.telephone = petOwnerDTO.telephone
    petOwner.address = petOwnerDTO.address

    return petOwner
}