package ar.edu.unsam.proyecto.vetappbackend.dto.user

import ar.edu.unsam.proyecto.vetappbackend.domain.user.*

data class VetDTO (
    val id: Int,
    val name: String,
    val surname: String,
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
        name = this.name,
        surname = this.surname,
        licence = this.licence,
        speciality = this.speciality,
        businessHours = this.businessHours,
        professionalEmail = this.professionalEmail,
        professionalAdress = this.professionalAdress,
        professionalTelephone = this.professionalTelephone,
        idUserData = this.userData.id!!
    )
}

fun VetDTO.fromJSON(userDataCurrent: UserData): Vet {
    val vetDTO = this
    return Vet().apply {
        id = vetDTO.id
        name = vetDTO.name
        surname = vetDTO.surname
        licence = vetDTO.licence
        speciality = vetDTO.speciality
        businessHours = vetDTO.businessHours
        professionalEmail = vetDTO.professionalEmail
        professionalAdress = vetDTO.professionalAdress
        professionalTelephone = vetDTO.professionalTelephone
        userData = userDataCurrent
    }
}
