package ar.edu.unsam.proyecto.vetappbackend.repository.user

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.Pet
import ar.edu.unsam.proyecto.vetappbackend.domain.user.Vet
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface VetRepository : CrudRepository<Vet, Int> {

    @Query(""" SELECT p FROM Vet v JOIN v.patients p WHERE v.id = :idVet """)
    fun findAllPetsByVetId(@Param("idVet") idVet:Int): List<Pet>

}