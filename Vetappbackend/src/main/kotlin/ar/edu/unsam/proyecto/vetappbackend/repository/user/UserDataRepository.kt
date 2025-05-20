package ar.edu.unsam.proyecto.vetappbackend.repository.user

import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserDataRepository : CrudRepository<UserData, Int> {

    fun findByAuthCredentialsId(idLogin: Int) : Optional<UserData>

}