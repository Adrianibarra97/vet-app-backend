package ar.edu.unsam.proyecto.vetappbackend.dto.user
import ar.edu.unsam.proyecto.vetappbackend.domain.type.TypeOfUser
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*

data class AuthCredentialsLoginDTO (val username: String, val password: String)

data class AuthCredentialsResponseDTO (val idUserData: Int, val typeOfUser: String)

data class AuthCredentialsDTO (
    val id: Int,
    val type: String,
    val username: String,
    val password: String,
    val idUserData: Int
)

fun AuthCredentials.toDTO(): AuthCredentialsDTO {
    return AuthCredentialsDTO(
        id = this.id,
        username = this.username!!,
        password = this.password!!,
        type = this.typeOfUser.toString(),
        idUserData = this.userData?.id!!
    )
}

fun AuthCredentialsDTO.fromJSON(currentUserData: UserData): AuthCredentials {
    val AuthCredentialsDTO = this
    return AuthCredentials().apply {
        this.id = AuthCredentialsDTO.id
        this.username = AuthCredentialsDTO.username
        this.password = AuthCredentialsDTO.password
        this.typeOfUser = TypeOfUser.valueOf(AuthCredentialsDTO.type)
        this.userData = currentUserData
    }
}