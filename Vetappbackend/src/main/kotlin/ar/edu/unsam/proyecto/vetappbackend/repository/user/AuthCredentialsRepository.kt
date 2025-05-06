package ar.edu.unsam.proyecto.vetappbackend.repository.user

import ar.edu.unsam.proyecto.vetappbackend.domain.user.AuthCredentials
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface AuthCredentialsRepository: CrudRepository<AuthCredentials, Int> {
    fun findByUsername(username: String): Optional<AuthCredentials>
}