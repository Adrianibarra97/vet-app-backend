package ar.edu.unsam.proyecto.vetappbackend.dto.user
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*

//Request&Response
data class PetOwnerDTO (
    //UserData
    val id: Int?,
    val dni: Int?,
    val name: String?,
    val surname: String?,
    val photo: String?,
    val email: String?,
    val telephone: String?,
    //PetOwner
    val emergencyContactName: String?,
    val emergencyContactPhone: String?,
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
    val password: String?
)

fun PetOwner.toDTO(): PetOwnerDTO {
    return PetOwnerDTO(
        //UserData
        id = this.id,
        dni = this.dni,
        name = this.name,
        surname = this.surname,
        photo = this.photo,
        email = this.email,
        telephone = this.telephone,
        //PetOwner
        emergencyContactName = this.emergencyContactName,
        emergencyContactPhone = this.emergencyContactPhone,
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

fun PetOwnerDTO.fromJSON(): PetOwner {
    val petOwnerDTO = this
    return PetOwner().apply {
        //UserData
        id = petOwnerDTO.id?: 0
        dni = petOwnerDTO.dni
        name = petOwnerDTO.name
        surname = petOwnerDTO.surname
        photo = petOwnerDTO.photo
        email = petOwnerDTO.email
        telephone = petOwnerDTO.telephone
        //PetOwner
        emergencyContactName = petOwnerDTO.emergencyContactName
        emergencyContactPhone = petOwnerDTO.emergencyContactPhone
        //LocationInfo
        locationInfo = LocationInfo(
            id = petOwnerDTO.idInfoLocation?: 0,
            locality = petOwnerDTO.locality,
            province = petOwnerDTO.province,
            country = petOwnerDTO.country,
            address = petOwnerDTO.address,
            postalCode = petOwnerDTO.postalCode
        )
        //AuthCredentials
        authCredentials = AuthCredentials(
            id = petOwnerDTO.idAuthCredentials?: 0,
            username = petOwnerDTO.username,
            password = petOwnerDTO.password,
            typeOfUser = TypeOfUser.PETOWNER
        )
    }
}