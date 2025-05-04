package ar.edu.unsam.proyecto.vetappbackend.dto.user

import ar.edu.unsam.proyecto.vetappbackend.domain.user.*

data class VetDTO (
    val id: Int,
    val name: String,
    val surname: String,
    val email: String,
    val address: String,
    val telephone: String,
    val licence: String,
    val speciality: String,
    val businessHours: String,
    val professionalEmail: String,
    val professionalAdress: String,
    val professionalTelephone: String,
    val idUserData: Int
)


fun Vet.toDTO(): VetDTO {
    return VetDTO(
        id = this.id!!,
        idUserData = this.authCredentials.id!!,
        name = this.name,
        surname = this.surname,
        email = this.email,
        address = this.address,
        telephone = this.telephone,
        licence = this.licence,
        speciality = this.speciality,
        businessHours = this.businessHours,
        professionalEmail = this.professionalEmail,
        professionalAdress = this.professionalAdress,
        professionalTelephone = this.professionalTelephone
    )
}

fun VetDTO.fromJSON(authCredentialsCurrent: AuthCredentials): Vet {
    val vetDTO = this
    return Vet().apply {
        id = vetDTO.id
        authCredentials = authCredentialsCurrent
        name = vetDTO.name
        surname = vetDTO.surname
        email = vetDTO.email
        address = vetDTO.address
        telephone = vetDTO.telephone
        licence = vetDTO.licence
        speciality = vetDTO.speciality
        businessHours = vetDTO.businessHours
        professionalEmail = vetDTO.professionalEmail
        professionalAdress = vetDTO.professionalAdress
        professionalTelephone = vetDTO.professionalTelephone
    }
}
