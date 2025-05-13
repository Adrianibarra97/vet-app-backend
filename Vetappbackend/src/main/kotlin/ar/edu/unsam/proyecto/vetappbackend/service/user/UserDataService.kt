package ar.edu.unsam.proyecto.vetappbackend.service.user

import ar.edu.unsam.proyecto.vetappbackend.domain.user.UserData
import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.repository.user.UserDataRepository
import ar.edu.unsam.proyecto.vetappbackend.service.BaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserDataService: BaseService<UserData> {

    @Autowired lateinit var userDataRepository: UserDataRepository

    override fun getOneById(idUserData: Int): UserData {
        return this.userDataRepository.findById(idUserData).orElseThrow {
            NotFoundException("No se encontró al usuario indicado: $idUserData")
        }
    }

    override fun getAll(): List<UserData> {
        return this.userDataRepository.findAll().toList()
    }


    override fun create(userData: UserData) {
        this.userDataRepository.save(userData)
    }

    override fun update(userData: UserData) {
        this.userDataRepository.save(userData)
    }

    override fun delete(userData: UserData) {
        this.userDataRepository.delete(userData)
    }

}