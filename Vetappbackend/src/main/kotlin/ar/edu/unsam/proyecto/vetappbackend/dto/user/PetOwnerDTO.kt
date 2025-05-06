package ar.edu.unsam.proyecto.vetappbackend.dto.user
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import java.time.LocalDate

data class PetOwnerDTO (
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

    val emergencyContactName: String,
    val emergencyContactPhone: String,

    val idAuthCredentials: Int,
    val username: String,
    val password: String
)

fun PetOwner.toDTO(): PetOwnerDTO {
    return PetOwnerDTO(
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
        emergencyContactName = this.emergencyContactName,
        emergencyContactPhone = this.emergencyContactPhone,
        idAuthCredentials = this.authCredentials.id!!,
        username = this.authCredentials.username,
        password = this.authCredentials.password
    )
}

fun PetOwnerDTO.fromJSON(authCredentialsCurrent: AuthCredentials): PetOwner {
    val petOwnerDTO = this
    return PetOwner().apply {
        id = petOwnerDTO.id
        dni = petOwnerDTO.dni
        name = petOwnerDTO.name
        surname = petOwnerDTO.surname
        email = petOwnerDTO.email
        address = petOwnerDTO.address
        telephone = petOwnerDTO.telephone
        country = petOwnerDTO.country
        province = petOwnerDTO.province
        locality = petOwnerDTO.locality
        postalCode = petOwnerDTO.postalCode
        emergencyContactName = petOwnerDTO.emergencyContactName
        emergencyContactPhone = petOwnerDTO.emergencyContactPhone

        authCredentials = authCredentialsCurrent
    }
}
