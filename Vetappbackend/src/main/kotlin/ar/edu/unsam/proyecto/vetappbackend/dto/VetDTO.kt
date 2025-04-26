package ar.edu.unsam.proyecto.vetappbackend.dto

import ar.edu.unsam.proyecto.vetappbackend.domain.user.*

data class VetDTO (
    val id: Int,
    val name: String,
    val surname: String,
    val username: String,
    val password: String,
    val licence: String,
    val speciality: String,
    val businessHours: String,
    val professionalEmail: String,
    val professionalAdress: String,
    val professionalTelephone: String
)


fun Vet.toDTO(): VetDTO {
    return VetDTO(
        id = this.id!!,
        name = this.name,
        surname = this.surname,
        username = this.username,
        password = this.password,
        licence = this.licence,
        speciality = this.speciality,
        businessHours = this.businessHours,
        professionalEmail = this.professionalEmail,
        professionalAdress = this.professionalAdress,
        professionalTelephone = this.professionalTelephone
    )
}

fun VetDTO.fromJSON(vetDTO: VetDTO): Vet {
    val vet = Vet()
    vet.id = vetDTO.id
    vet.name = vetDTO.name
    vet.surname = vetDTO.surname
    vet.username = vetDTO.username
    vet.password = vetDTO.password
    vet.licence = vetDTO.licence
    vet.speciality = vetDTO.speciality
    vet.businessHours = vetDTO.businessHours
    vet.professionalEmail = vetDTO.professionalEmail
    vet.professionalAdress = vetDTO.professionalAdress
    vet.professionalTelephone = vetDTO.professionalTelephone

    return vet
}