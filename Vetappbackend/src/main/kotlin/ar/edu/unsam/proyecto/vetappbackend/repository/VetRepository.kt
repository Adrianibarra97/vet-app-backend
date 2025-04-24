package ar.edu.unsam.proyecto.vetappbackend.repository

import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface VetRepository : CrudRepository<Vet, Int> {

    @Query(""" SELECT p FROM Vet v JOIN v.patients p WHERE v.id = :idVet """)
    fun findAllPetsByVetId(
        @Param("idVet") idVet: Int
    ): List<Pet>

    @Query("""
    SELECT DISTINCT p
    FROM Vet v
        JOIN v.patients p
        LEFT JOIN MedicalHistory mh ON mh.pet.id = p.id  
        LEFT JOIN Vaccine vac ON vac.medicalHistory.id = mh.id
        LEFT JOIN MedicalShift ms ON ms.pet.id = p.id AND ms.vet.id = :idVet
    WHERE v.id = :idVet
        AND (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')))
        AND (:hasPendingVaccine IS NULL 
            OR (:hasPendingVaccine = FALSE) 
            OR (:hasPendingVaccine = TRUE AND vac.completed = FALSE)
        )
        AND (:hasMedicalShift IS NULL OR  
            (:hasMedicalShift = FALSE) OR 
            (:hasMedicalShift = TRUE AND ms IS NOT NULL)
        )
    """)
    fun getAllByFilter(
        @Param("idVet") idVet: Int,
        @Param("name") name: String?,
        @Param("hasMedicalShift") hasMedicalShift: Boolean?,
        @Param("hasPendingVaccine") hasPendingVaccine: Boolean?
    ): List<Pet>

}

