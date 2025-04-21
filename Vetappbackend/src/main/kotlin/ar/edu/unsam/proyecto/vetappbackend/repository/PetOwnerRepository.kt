package ar.edu.unsam.proyecto.vetappbackend.repository

import ar.edu.unsam.proyecto.vetappbackend.domain.Pet
import ar.edu.unsam.proyecto.vetappbackend.domain.PetOwner
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param


interface PetOwnerRepository : CrudRepository<PetOwner, Int> {
    @Query("""
        SELECT p FROM PetOwner po
        JOIN po.pets p
        WHERE po.id = :idOwner
    """)
    fun findAllPetsByOwnerId(@Param("idOwner") idOwner: Int): List<Pet>
}