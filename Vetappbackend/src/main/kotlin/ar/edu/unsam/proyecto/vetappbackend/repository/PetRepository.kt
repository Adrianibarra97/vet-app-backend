package ar.edu.unsam.proyecto.vetappbackend.repository

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.Pet
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PetRepository : CrudRepository<Pet, Int> {

    @Modifying
    @Transactional
    @Query(value = "SELECT delete_pet_relations(:idPet)", nativeQuery = true)
    fun callDeletePetRelationsFunction(@Param("idPet") idPet: Int)


}
