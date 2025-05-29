package ar.edu.unsam.proyecto.vetappbackend.dto.user
import ar.edu.unsam.proyecto.vetappbackend.domain.type.*
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*

data class AuthCredentialsLoginDTO (val username: String, val password: String)

data class AuthCredentialsResponseDTO (val authCredentialsID: Int, val typeOfUser: String)

data class AuthCredentialsDTO (
    val id: Int?,
    val username: String,
    val password: String,
    val type: String,
)

fun AuthCredentials.toDTO(): AuthCredentialsDTO {
    return AuthCredentialsDTO(
        id = this.id,
        username = this.username!!,
        password = this.password!!,
        type = this.typeOfUser.toString()
    )
}

fun AuthCredentialsDTO.fromJSON(): AuthCredentials {
    val AuthCredentialsDTO = this
    return AuthCredentials().apply {
        this.id = AuthCredentialsDTO.id!!
        this.username = AuthCredentialsDTO.username
        this.password = AuthCredentialsDTO.password
        this.typeOfUser = TypeOfUser.valueOf(AuthCredentialsDTO.type)
    }

}

data class ValidAuthchredentialsResponseDTO(
    val id: Int?,
    val validCode: String
)
