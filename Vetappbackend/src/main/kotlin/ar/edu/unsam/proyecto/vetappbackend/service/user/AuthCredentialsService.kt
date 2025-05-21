package ar.edu.unsam.proyecto.vetappbackend.service.user

import ar.edu.unsam.proyecto.vetappbackend.domain.type.TypeOfUser
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import ar.edu.unsam.proyecto.vetappbackend.dto.user.*
import ar.edu.unsam.proyecto.vetappbackend.error.*
import ar.edu.unsam.proyecto.vetappbackend.repository.user.*
import ar.edu.unsam.proyecto.vetappbackend.service.notification.EmailService
import org.apache.catalina.User
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus
import java.util.*

@Service
class AuthCredentialsService {

    @Autowired private lateinit var authCredentialsRepository: AuthCredentialsRepository

    @Autowired lateinit var emailService: EmailService

    @Autowired lateinit var userDataService: UserDataService



    fun getAll(): List<AuthCredentials> {
        return this.authCredentialsRepository.findAll().toList()
    }

    fun getOneById(idAuthCredentials: Int): AuthCredentials {
        return this.authCredentialsRepository.findById(idAuthCredentials).orElseThrow {
            NotFoundException("No se encontró al usuario indicado: $idAuthCredentials")
        }
    }

    fun login(authCredentialsLoginDTO: AuthCredentialsLoginDTO): AuthCredentialsResponseDTO {
        val authCredentials: AuthCredentials = this.findByUsername(authCredentialsLoginDTO.username)
        this.verifyPassword(authCredentialsLoginDTO.password, authCredentials.password!!)
        return AuthCredentialsResponseDTO(authCredentials.id, authCredentials.typeOfUser!!.name)
    }

    fun resetPassword(username: String): String {

        val authCredentials: AuthCredentials = this.findByUsername(username)
        val verificationCode = this.generateVerificationCode()
        var user: UserData = this.userDataService.findByAuthCredentialsId(authCredentials.id)

        this.emailService.sendVerificationCode(user, verificationCode)
        return verificationCode
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

    private fun generateVerificationCode(): String {
        return UUID.randomUUID().toString().substring(0, 6)
    }

}