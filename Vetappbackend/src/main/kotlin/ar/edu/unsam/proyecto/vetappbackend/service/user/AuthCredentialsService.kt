package ar.edu.unsam.proyecto.vetappbackend.service.user

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import ar.edu.unsam.proyecto.vetappbackend.dto.user.*
import ar.edu.unsam.proyecto.vetappbackend.service.*
import ar.edu.unsam.proyecto.vetappbackend.error.*
import ar.edu.unsam.proyecto.vetappbackend.repository.user.*
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus


@Service
class AuthCredentialsService: BaseService<AuthCredentials> {

    @Autowired private lateinit var authCredentialsRepository: AuthCredentialsRepository

    override fun getOneById(idAuthCredentials: Int): AuthCredentials {
        return this.authCredentialsRepository.findById(idAuthCredentials).orElseThrow {
            NotFoundException("No se encontró al usuario indicado: $idAuthCredentials")
        }
    }

    override fun getAll(): List<AuthCredentials> {
        return this.authCredentialsRepository.findAll().toList()
    }

    override fun delete(deleteAuthCredentials: AuthCredentials) {
        this.authCredentialsRepository.delete(deleteAuthCredentials)
    }

    override fun create(newAuthCredentials: AuthCredentials) {
        this.verifyCreate(newAuthCredentials)
        this.authCredentialsRepository.save(newAuthCredentials)
    }

    override fun update(updateAuthCredentials: AuthCredentials) {
        this.verifyUpdate(updateAuthCredentials)
        this.authCredentialsRepository.save(updateAuthCredentials)
    }



    fun login(authCredentialsLoginDTO: AuthCredentialsLoginDTO): AuthCredentialsResponseDTO {
        val authCredentials: AuthCredentials = this.findByUsername(authCredentialsLoginDTO.username)
        this.verifyPassword(authCredentialsLoginDTO.password, authCredentials.password!!)
        return AuthCredentialsResponseDTO(authCredentials.userData!!.id, authCredentials.typeOfUser!!.name)
    }

    fun verifyCreate(authCredentials: AuthCredentials) {
        if (authCredentialsRepository.findByUsername(authCredentials.username!!).isPresent) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Username en uso: ${authCredentials.username!!}")
        }
    }

    fun verifyUpdate(authCredentials: AuthCredentials) {
        val existing = getOneById(authCredentials.id)
        if (authCredentials.username != existing.username) {
            verifyCreate(authCredentials)
        }
    }

    private fun verifyPassword(testPassword: String, currentPassword: String) {
        if (testPassword != currentPassword) {
            throw CredencialesInvalidasException()
        }
    }

    private fun findByUsername(username: String): AuthCredentials {
        return authCredentialsRepository.findByUsername(username).orElseThrow {
            CredencialesInvalidasException()
        }
    }


}