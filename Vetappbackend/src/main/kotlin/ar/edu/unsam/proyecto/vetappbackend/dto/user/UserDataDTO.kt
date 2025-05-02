package ar.edu.unsam.proyecto.vetappbackend.dto.user
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*

data class UserDataDTO (
    val id: Int,
    val username: String,
    val password: String,
    val type: String,
)

fun UserData.toDTO(): UserDataDTO {
    return UserDataDTO(
        id = this.id!!,
        username = this.username,
        password = this.password,
        type = this.typeOfUser.toString()
    )
}

fun UserDataDTO.fromJSON(): UserData {
    val userDataDTO = this
    return UserData().apply {
        this.id = userDataDTO.id
        this.username = userDataDTO.username
        this.password = userDataDTO.password
        this.typeOfUser = TypeOfUser.valueOf(userDataDTO.type)
    }
}