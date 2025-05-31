package ar.edu.unsam.proyecto.vetappbackend.service.user

import java.util.*
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.server.ResponseStatusException
import ar.edu.unsam.proyecto.vetappbackend.repository.user.*
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import ar.edu.unsam.proyecto.vetappbackend.dto.user.*
import ar.edu.unsam.proyecto.vetappbackend.error.*
import ar.edu.unsam.proyecto.vetappbackend.service.notification.EmailService

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

    fun resetPassword(username: String): ValidAuthchredentialsResponseDTO {
        try {
            val authCredentials: AuthCredentials = this.findByUsername(username)
            val verificationCode = this.generateVerificationCode()
            val response = ValidAuthchredentialsResponseDTO(authCredentials.id,verificationCode)
            var user: UserData = this.userDataService.findByAuthCredentialsId(authCredentials.id)

            this.emailService.sendVerificationCode(user, verificationCode)
            return response
        }catch(e: Exception){
            return ValidAuthchredentialsResponseDTO(-1,"")
        }

    }

    @Transactional
    fun updatePassword(newPassword: String, idAuthCredentials: Int) {
        val authCredentials: AuthCredentials = this.getOneById(idAuthCredentials).apply { password = newPassword }
        val user: UserData = this.userDataService.findByAuthCredentialsId(authCredentials.id)
        this.authCredentialsRepository.save(authCredentials)
        this.emailService.updatePassword(user)
    }

    fun verifyUsernameCreate(username: String) {
        if (authCredentialsRepository.findByUsername(username).isPresent) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Username en uso: $username")
        }
    }

    fun verifyUsernameUpdate(idAuthCredentials: Int, username: String) {
        val existingAuthCredentials = this.getOneById(idAuthCredentials)
        if (existingAuthCredentials.username != username) {
            verifyUsernameCreate(username)
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