package ar.edu.unsam.proyecto.vetappbackend.repository

import ar.edu.unsam.proyecto.vetappbackend.domain.Pet
import ar.edu.unsam.proyecto.vetappbackend.domain.Vet
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.time.LocalDate

interface VetRepository : CrudRepository<Vet, Int> {

    @Query(""" 
        SELECT p 
        FROM Vet v 
        JOIN v.patients p 
        WHERE v.id = :idVet """)
    fun findAllPetsByVetId(
        @Param("idVet") idVet: Int
    ): List<Pet>

    @Query("""
    SELECT DISTINCT p
    FROM Vet v
        JOIN v.patients p
        JOIN p.medicalHistory mh
        LEFT JOIN mh.vaccines vac
    WHERE v.id = :idVet
        AND (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')))
        AND (:hasPendingVaccine IS NULL OR
            (:hasPendingVaccine = FALSE) OR 
            (:hasPendingVaccine = TRUE AND vac.completed = FALSE)
        )
    """)
    fun getAllByFilter(
        @Param("idVet") idVet: Int,
        @Param("name") name: String?,
        @Param("hasPendingVaccine") hasPendingVaccine: Boolean?
    ): List<Pet>

}

