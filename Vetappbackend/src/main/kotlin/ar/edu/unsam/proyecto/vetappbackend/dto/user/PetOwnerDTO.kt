package ar.edu.unsam.proyecto.vetappbackend.dto.user
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import java.time.LocalDate

data class PetOwnerDTO (
    val id: Int,
    val idAuthCredentials: Int,
    val name: String,
    val surname: String,
    val email: String,
    val telephone: String,
    val address: String,
    val dni: Int,
    val age: Int,
    val petCount: Int,
    val birthdate: String,
    val username: String,
    val landline: String

)

fun PetOwner.toDTO(): PetOwnerDTO {
    return PetOwnerDTO(
        id = this.id!!,
        idAuthCredentials = this.authCredentials.id!!,
        name = this.name,
        surname = this.surname,
        email = this.email,
        telephone = this.telephone,
        address = this.address,
        dni = this.dni,
        age = this.age,
        petCount = this.petCount,
        birthdate = this.birthdate.toString(),
        username = this.username,
        landline = this.landline
    )
}

fun PetOwnerDTO.fromJSON(authCredentialsCurrent: AuthCredentials): PetOwner {
    val petOwnerDTO = this
    return PetOwner().apply {
        id = petOwnerDTO.id
        name = petOwnerDTO.name
        surname = petOwnerDTO.surname
        email = petOwnerDTO.email
        telephone = petOwnerDTO.telephone
        address = petOwnerDTO.address
        authCredentials = authCredentialsCurrent
        dni = petOwnerDTO.dni
        age = petOwnerDTO.age
        petCount = petOwnerDTO.petCount
        birthdate = LocalDate.parse(petOwnerDTO.birthdate.toString())
    }
}
