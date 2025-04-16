package ar.edu.unsam.proyecto.vetappbackend.repository

import ar.edu.unsam.proyecto.vetappbackend.domain.UserData
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserDataRepository: CrudRepository<UserData, Int> {
}