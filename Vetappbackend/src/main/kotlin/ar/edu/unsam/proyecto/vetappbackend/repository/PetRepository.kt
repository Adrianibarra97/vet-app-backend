package ar.edu.unsam.proyecto.vetappbackend.repository

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.Pet
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PetRepository : CrudRepository<Pet, Int> {

}