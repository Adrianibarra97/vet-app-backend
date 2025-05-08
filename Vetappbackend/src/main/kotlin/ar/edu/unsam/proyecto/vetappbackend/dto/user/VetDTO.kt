package ar.edu.unsam.proyecto.vetappbackend.dto.user
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*

data class VetDTO (
    //UserData
    val id: Int?,
    val dni: Int?,
    val name: String?,
    val surname: String?,
    val photo: String?,
    val email: String?,
    val telephone: String?,
    //Vet
    val licence: String?,
    val speciality: String?,
    val businessHours: String?,
    val professionalEmail: String?,
    val professionalAddress: String?,
    val professionalTelephone: String?,
    val professionalLocality: String?,
    val professionalPostalCode: String?,
    //LocationInfo
    val idInfoLocation: Int?,
    val address: String?,
    val country: String?,
    val province: String?,
    val locality: String?,
    val postalCode: String?,
    //AuthCredentials
    val idAuthCredentials: Int?,
    val username: String?,
    val password: String?,
)

fun Vet.toDTO(): VetDTO {
    return VetDTO(
        //UserData
        id = this.id,
        dni = this.dni,
        name = this.name,
        surname = this.surname,
        photo = this.photo,
        email = this.email,
        telephone = this.telephone,
        //Vet
        licence = this.licence,
        speciality = this.speciality,
        businessHours = this.businessHours,
        professionalEmail = this.professionalEmail,
        professionalAddress = this.professionalAddress,
        professionalTelephone = this.professionalTelephone,
        professionalLocality = this.professionalLocality,
        professionalPostalCode = this.professionalPostalCode,
        //LocationInfo
        idInfoLocation = this.locationInfo.id,
        address = this.locationInfo.address,
        country = this.locationInfo.country,
        province = this.locationInfo.province,
        locality = this.locationInfo.locality,
        postalCode = this.locationInfo.postalCode,
        //AuthCredentials
        idAuthCredentials = this.authCredentials.id,
        username = this.authCredentials.username,
        password = this.authCredentials.password
    )
}

fun VetDTO.fromJSON(): Vet {
    val vetDTO = this
    return Vet().apply {
        //UserData
        id = vetDTO.id?: 0
        dni = vetDTO.dni
        name = vetDTO.name
        surname = vetDTO.surname
        photo = vetDTO.photo
        email = vetDTO.email
        telephone = vetDTO.telephone
        //Vet
        licence = vetDTO.licence
        speciality = vetDTO.speciality
        businessHours = vetDTO.businessHours
        professionalEmail = vetDTO.professionalEmail
        professionalAddress = vetDTO.professionalAddress
        professionalTelephone = vetDTO.professionalTelephone
        professionalLocality = vetDTO.professionalLocality
        professionalPostalCode = vetDTO.professionalPostalCode
        //LocationInfo
        locationInfo = LocationInfo(
            id = vetDTO.idInfoLocation?: 0,
            locality = vetDTO.locality,
            province = vetDTO.province,
            country = vetDTO.country,
            address = vetDTO.address,
            postalCode = vetDTO.postalCode
        )
        //AuthCredentials
        authCredentials = AuthCredentials(
            id = vetDTO.idAuthCredentials ?: 0,
            username = vetDTO.username,
            password = vetDTO.password,
            typeOfUser = TypeOfUser.VET
        )
    }
}
