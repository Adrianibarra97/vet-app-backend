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

    @Query("""
    SELECT DISTINCT p
    FROM PetOwner po
        JOIN po.pets p
        JOIN p.medicalHistory mh
        LEFT JOIN mh.vaccines vac
        LEFT JOIN MedicalShift ms ON ms.patient.id = p.id
    WHERE po.id = :idOwner
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
        @Param("idOwner") idOwner: Int,
        @Param("name") name: String?,
        @Param("hasMedicalShift") hasMedicalShift: Boolean?,
        @Param("hasPendingVaccine") hasPendingVaccine: Boolean?
    ): List<Pet>
}