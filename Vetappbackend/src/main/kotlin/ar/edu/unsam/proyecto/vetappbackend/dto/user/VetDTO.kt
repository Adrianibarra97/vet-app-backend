package ar.edu.unsam.proyecto.vetappbackend.dto.user

import ar.edu.unsam.proyecto.vetappbackend.domain.user.*

data class VetDTO (
    val id: Int,
    val dni: Int,
    val name: String,
    val surname: String,
    val photo: String,
    val email: String,
    val address: String,
    val telephone: String,
    val country: String,
    val province: String,
    val locality: String,
    val postalCode: String,

    val licence: String,
    val speciality: String,
    val businessHours: String,
    val professionalEmail: String,
    val professionalAddress: String,
    val professionalTelephone: String,
    val professionalLocality: String,
    val professionalPostalCode: String,

    val idAuthCredentials: Int,
    val username: String,
    val password: String
)


fun Vet.toDTO(): VetDTO {
    return VetDTO(
        id = this.id!!,
        dni = this.dni,
        name = this.name,
        surname = this.surname,
        photo = this.photo,
        email = this.email,
        address = this.address,
        telephone = this.telephone,
        country = this.country,
        province = this.province,
        locality = this.locality,
        postalCode = this.postalCode,

        licence = this.licence,
        speciality = this.speciality,
        businessHours = this.businessHours,
        professionalEmail = this.professionalEmail,
        professionalAddress = this.professionalAddress,
        professionalTelephone = this.professionalTelephone,
        professionalLocality = this.professionalLocality,
        professionalPostalCode = this.professionalPostalCode,

        idAuthCredentials = this.authCredentials.id!!,
        username = this.authCredentials.username,
        password = this.authCredentials.password
    )
}

fun VetDTO.fromJSON(authCredentialsCurrent: AuthCredentials): Vet {
    val vetDTO = this
    return Vet().apply {
        id = vetDTO.id
        dni = vetDTO.dni
        name = vetDTO.name
        surname = vetDTO.surname
        photo = vetDTO.photo
        email = vetDTO.email
        address = vetDTO.address
        telephone = vetDTO.telephone
        country = vetDTO.country
        province = vetDTO.province
        locality = vetDTO.locality
        postalCode = vetDTO.postalCode

        licence = vetDTO.licence
        speciality = vetDTO.speciality
        businessHours = vetDTO.businessHours
        professionalEmail = vetDTO.professionalEmail
        professionalAddress = vetDTO.professionalAddress
        professionalTelephone = vetDTO.professionalTelephone
        professionalLocality = vetDTO.professionalLocality
        professionalPostalCode = vetDTO.professionalPostalCode

        authCredentials = authCredentialsCurrent
    }
}
