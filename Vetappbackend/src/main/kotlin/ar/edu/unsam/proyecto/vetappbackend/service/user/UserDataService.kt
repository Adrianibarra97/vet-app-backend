package ar.edu.unsam.proyecto.vetappbackend.service.user

import ar.edu.unsam.proyecto.vetappbackend.repository.user.UserDataRepository
import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.domain.user.UserData
import ar.edu.unsam.proyecto.vetappbackend.service.BaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserDataService: BaseService<UserData> {

    @Autowired lateinit var userDataRepository: UserDataRepository

    override fun getOneById(idUserData: Int): UserData {
        return this.userDataRepository.findById(idUserData).orElseThrow {
            NotFoundException("No se encontró al petOwner indicado: $idUserData")
        }
    }

    override fun getAll(): List<UserData> {
        return this.userDataRepository.findAll().toList()
    }

    override fun create(newUserData: UserData) {
        this.userDataRepository.save(newUserData)
    }

    override fun update(updateUserData: UserData) {
        this.userDataRepository.save(updateUserData)
    }

    override fun delete(deleteUserData: UserData) {
        this.userDataRepository.delete(deleteUserData)
    }

    fun findByAuthCredentialsId(idAuthCredentials: Int): UserData {
        return this.userDataRepository.findByAuthCredentialsId(idAuthCredentials).orElseThrow {
            NotFoundException("No se encontró los datos del usuario: $idAuthCredentials")
        }
    }

}