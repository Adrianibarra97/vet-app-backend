package ar.edu.unsam.proyecto.vetappbackend.service.user

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import ar.edu.unsam.proyecto.vetappbackend.dto.user.*
import ar.edu.unsam.proyecto.vetappbackend.service.*
import ar.edu.unsam.proyecto.vetappbackend.error.*
import ar.edu.unsam.proyecto.vetappbackend.repository.user.*

@Service
class UserDataService : BaseService<UserData> {

    @Autowired private lateinit var userDataRepository: UserDataRepository

    override fun getOneById(idUserData: Int): UserData {
        return this.userDataRepository.findById(idUserData).orElseThrow {
            NotFoundException("No se encontró al usuario indicado: $idUserData")
        }
    }

    override fun getAll(): List<UserData> {
        return this.userDataRepository.findAll().toList()
    }

    override fun create(newUserData: UserData) {
        this.userDataRepository.save(newUserData)
    }

    override fun delete(userDataDelete: UserData) {
        this.userDataRepository.delete(userDataDelete)
    }

    override fun update(userDataUpdate: UserData) {
        this.getOneById(userDataUpdate.id!!)
        this.userDataRepository.save(userDataUpdate)
    }

    fun login(userLoginDTO: UserDataLoginDTO): UserDataResponseDTO {
        val userData: UserData = this.findByUsername(userLoginDTO.username)
        this.verifyPassword(userLoginDTO.password, userData.password)
        return UserDataResponseDTO(userData.id!!, userData.typeOfUser.name)
    }

    private fun findByUsername(username: String): UserData {
        return userDataRepository.findByUsername(username).orElseThrow {
            CredencialesInvalidasException()
        }
    }

    private fun verifyPassword(testPassword: String, currentPassword: String) {
        if (testPassword != currentPassword) {
            throw CredencialesInvalidasException()
        }
    }

}