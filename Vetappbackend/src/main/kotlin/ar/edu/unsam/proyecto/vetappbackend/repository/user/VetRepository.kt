package ar.edu.unsam.proyecto.vetappbackend.repository.user

import java.util.Optional
import ar.edu.unsam.proyecto.vetappbackend.domain.user.Vet
import org.springframework.data.repository.CrudRepository

interface VetRepository : CrudRepository<Vet, Int> {

    fun findByAuthCredentialsId(idLogin: Int) : Optional<Vet>

}