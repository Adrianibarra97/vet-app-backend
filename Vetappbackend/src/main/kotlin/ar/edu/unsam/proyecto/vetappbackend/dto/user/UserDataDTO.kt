package ar.edu.unsam.proyecto.vetappbackend.dto.user

data class UserDataLoginDTO (
    val username: String,
    val password: String
)

data class UserDataResponseDTO (
    val userLogedID: Long?,
    val tipoUsuario: String,
)