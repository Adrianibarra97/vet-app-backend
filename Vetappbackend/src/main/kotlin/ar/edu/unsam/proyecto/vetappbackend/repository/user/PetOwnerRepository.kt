package ar.edu.unsam.proyecto.vetappbackend.repository.user

import java.util.Optional
import org.springframework.data.repository.CrudRepository
import ar.edu.unsam.proyecto.vetappbackend.domain.user.PetOwner

interface PetOwnerRepository : CrudRepository<PetOwner, Int>{

    fun findByAuthCredentialsId(idLogin: Int) : Optional<PetOwner>

}