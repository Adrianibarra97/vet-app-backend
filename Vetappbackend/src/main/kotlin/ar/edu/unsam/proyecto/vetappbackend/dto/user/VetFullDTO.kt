package ar.edu.unsam.proyecto.vetappbackend.dto.user
import ar.edu.unsam.proyecto.vetappbackend.domain.type.*
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*

data class VetFullDTO (
    val id: Int,
    val dni: Int,
    val name: String,
    val surname: String,
    val photo: String,
    val email: String,
    val telephone: String,
    val licence: String,
    val speciality: String,
    val businessHours: String,
    val professionalEmail: String,
    val professionalAddress: String,
    val professionalTelephone: String,
    val professionalLocality: String,
    val professionalPostalCode: String,
    //LocationInfo
    val idInfoLocation: Int,
    val address: String,
    val country: String,
    val province: String,
    val locality: String,
    val postalCode: String,
    //AuthCredentials
    val idAuthCredentials: Int,
    val username: String,
    val password: String
)

fun Vet.toFullDTO(): VetFullDTO {
    return VetFullDTO(
        id = this.id,
        dni = this.dni!!,
        name = this.name!!,
        surname = this.surname!!,
        photo = this.photo!!,
        email = this.email!!,
        telephone = this.telephone!!,
        licence = this.licence!!,
        speciality = this.speciality!!,
        businessHours = this.businessHours!!,
        professionalEmail = this.professionalEmail!!,
        professionalAddress = this.professionalAddress!!,
        professionalTelephone = this.professionalTelephone!!,
        professionalLocality = this.professionalLocality!!,
        professionalPostalCode = this.professionalPostalCode!!,
        //LocationInfo
        idInfoLocation = this.locationInfo?.id!!,
        address = this.locationInfo?.address!!,
        country = this.locationInfo?.country!!,
        province = this.locationInfo?.province!!,
        locality = this.locationInfo?.locality!!,
        postalCode = this.locationInfo?.postalCode!!,
        //AuthCredentials
        idAuthCredentials = this.authCredentials?.id!!,
        username = this.authCredentials?.username!!,
        password = this.authCredentials?.password!!
    )
}

fun VetFullDTO.fromJSON(): Vet {
    val vetDTO = this
    val vet = Vet().apply {
        id = vetDTO.id
        dni = vetDTO.dni
        name = vetDTO.name
        surname = vetDTO.surname
        photo = vetDTO.photo
        email = vetDTO.email
        telephone = vetDTO.telephone
        licence = vetDTO.licence
        speciality = vetDTO.speciality
        businessHours = vetDTO.businessHours
        professionalEmail = vetDTO.professionalEmail
        professionalAddress = vetDTO.professionalAddress
        professionalTelephone = vetDTO.professionalTelephone
        professionalLocality = vetDTO.professionalLocality
        professionalPostalCode = vetDTO.professionalPostalCode
        locationInfo = vetDTO.fromLocationInfoJSON()
        authCredentials = vetDTO.fromAuthCredentialsJSON()
    }
    vet.locationInfo!!.userData = vet
    vet.authCredentials!!.userData = vet
    return vet
}

fun VetFullDTO.fromLocationInfoJSON(): LocationInfo {
    val vetDTO = this
    return LocationInfo(
        id = vetDTO.idInfoLocation,
        address = vetDTO.address,
        country = vetDTO.country,
        province = vetDTO.province,
        locality = vetDTO.locality,
        postalCode = vetDTO.postalCode
    )
}

fun VetFullDTO.fromAuthCredentialsJSON(): AuthCredentials {
    val vetDTO = this
    return AuthCredentials(
        id = vetDTO.idAuthCredentials,
        username = vetDTO.username,
        password = vetDTO.password,
        typeOfUser = TypeOfUser.PETOWNER
    )
}