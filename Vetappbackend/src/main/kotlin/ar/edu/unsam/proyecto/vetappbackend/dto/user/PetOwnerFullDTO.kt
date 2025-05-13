package ar.edu.unsam.proyecto.vetappbackend.dto.user

import ar.edu.unsam.proyecto.vetappbackend.domain.type.TypeOfUser
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*

data class PetOwnerFullDTO (
    val id: Int,
    val dni: Int,
    val name: String,
    val surname: String,
    val photo: String,
    val email: String,
    val telephone: String,
    val emergencyContactName: String,
    val emergencyContactPhone: String,
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


fun PetOwner.toFullDTO(): PetOwnerFullDTO {
    return PetOwnerFullDTO(
        id = this.id,
        dni = this.dni!!,
        name = this.name!!,
        surname = this.surname!!,
        photo = this.photo!!,
        email = this.email!!,
        telephone = this.telephone!!,
        emergencyContactName = this.emergencyContactName!!,
        emergencyContactPhone = this.emergencyContactPhone!!,
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

fun PetOwnerFullDTO.fromJSON(): PetOwner {
    val petOwnerDTO = this
    val petOwner = PetOwner().apply {
        id = petOwnerDTO.id
        dni = petOwnerDTO.dni
        name = petOwnerDTO.name
        surname = petOwnerDTO.surname
        photo = petOwnerDTO.photo
        email = petOwnerDTO.email
        telephone = petOwnerDTO.telephone
        emergencyContactName = petOwnerDTO.emergencyContactName
        emergencyContactPhone = petOwnerDTO.emergencyContactPhone
        locationInfo = petOwnerDTO.fromLocationInfoJSON()
        authCredentials = petOwnerDTO.fromAuthCredentialsJSON()
    }
    petOwner.locationInfo!!.userData = petOwner
    petOwner.authCredentials!!.userData = petOwner
    return petOwner
}

fun PetOwnerFullDTO.fromLocationInfoJSON(): LocationInfo {
    val petOwnerDTO = this
    return LocationInfo(
        id = petOwnerDTO.idInfoLocation,
        address = petOwnerDTO.address,
        country = petOwnerDTO.country,
        province = petOwnerDTO.province,
        locality = petOwnerDTO.locality,
        postalCode = petOwnerDTO.postalCode
    )
}

fun PetOwnerFullDTO.fromAuthCredentialsJSON(): AuthCredentials {
    val petOwnerDTO = this
    return AuthCredentials(
        id = petOwnerDTO.idAuthCredentials,
        username = petOwnerDTO.username,
        password = petOwnerDTO.password,
        typeOfUser = TypeOfUser.PETOWNER
    )
}