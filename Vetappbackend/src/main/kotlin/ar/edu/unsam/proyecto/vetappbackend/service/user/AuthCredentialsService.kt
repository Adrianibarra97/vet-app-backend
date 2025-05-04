package ar.edu.unsam.proyecto.vetappbackend.service.user

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import ar.edu.unsam.proyecto.vetappbackend.dto.user.*
import ar.edu.unsam.proyecto.vetappbackend.service.*
import ar.edu.unsam.proyecto.vetappbackend.error.*
import ar.edu.unsam.proyecto.vetappbackend.repository.user.*

@Service
class AuthCredentialsService : BaseService<AuthCredentials> {

    @Autowired private lateinit var authCredentialsRepository: AuthCredentialsRepository

    override fun getOneById(idAuthCredentials: Int): AuthCredentials {
        return this.authCredentialsRepository.findById(idAuthCredentials).orElseThrow {
            NotFoundException("No se encontró al usuario indicado: $idAuthCredentials")
        }
    }

    override fun getAll(): List<AuthCredentials> {
        return this.authCredentialsRepository.findAll().toList()
    }

    override fun create(newAuthCredentials: AuthCredentials) {
        this.authCredentialsRepository.save(newAuthCredentials)
    }

    override fun delete(authCredentialsDelete: AuthCredentials) {
        this.authCredentialsRepository.delete(authCredentialsDelete)
    }

    override fun update(authCredentialsUpdate: AuthCredentials) {
        this.getOneById(authCredentialsUpdate.id!!)
        this.authCredentialsRepository.save(authCredentialsUpdate)
    }

    fun login(userLoginDTO: AuthCredentialsLoginDTO): AuthCredentialsResponseDTO {
        val authCredentials: AuthCredentials = this.findByUsername(userLoginDTO.username)
        this.verifyPassword(userLoginDTO.password, authCredentials.password)
        return AuthCredentialsResponseDTO(authCredentials.id!!, authCredentials.typeOfUser.name)
    }

    private fun findByUsername(username: String): AuthCredentials {
        return authCredentialsRepository.findByUsername(username).orElseThrow {
            CredencialesInvalidasException()
        }
    }

    private fun verifyPassword(testPassword: String, currentPassword: String) {
        if (testPassword != currentPassword) {
            throw CredencialesInvalidasException()
        }
    }

}