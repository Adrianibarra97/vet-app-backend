package ar.edu.unsam.proyecto.vetappbackend.repository.pet

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.Pet
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PetRepository : CrudRepository<Pet, Int> {

    @Query("SELECT p FROM Pet p WHERE p.petOwner.id = :idOwner")
    fun findAllPetsByOwnerId(@Param("idOwner") idOwner: Int): List<Pet>

    @Query("SELECT p FROM Pet p JOIN p.vets v WHERE v.id = :idVet")
    fun findAllPetsByVetId(@Param("idVet") idVet: Int): List<Pet>

    @Query("""
        SELECT DISTINCT p
        FROM Pet p 
            LEFT JOIN MedicalHistory mh ON mh.pet.id = p.id  
            LEFT JOIN MedicalShift ms ON ms.pet.id = mh.pet.id 
            LEFT JOIN Vaccine vac ON vac.medicalHistory.id = mh.id 
        WHERE p.petOwner.id = :idOwner
            AND (:name IS NULL OR LOWER(p.name) LIKE '%' || LOWER(CAST(:name AS string)) || '%')
            AND (:hasMedicalShift IS NULL OR (:hasMedicalShift = FALSE) OR (:hasMedicalShift = TRUE AND ms IS NOT NULL))
            AND (:hasPendingVaccine IS NULL OR (:hasPendingVaccine = FALSE) OR (:hasPendingVaccine = TRUE AND vac.completed = FALSE))
    """)
    fun getThisOwnersPetFilter(
        @Param("idOwner") idOwner: Int,
        @Param("name") name: String?,
        @Param("hasMedicalShift") hasMedicalShift: Boolean?,
        @Param("hasPendingVaccine") hasPendingVaccine: Boolean?
    ): List<Pet>

    @Query("""
        SELECT DISTINCT p
        FROM Pet p JOIN p.vets v
            LEFT JOIN MedicalHistory mh ON mh.pet.id = p.id  
            LEFT JOIN MedicalShift ms ON ms.pet.id = p.id AND ms.vet.id = v.id
            LEFT JOIN Vaccine vac ON vac.medicalHistory.id = mh.id
        WHERE v.id = :idVet
            AND (:name IS NULL OR LOWER(p.name) LIKE '%' || LOWER(CAST(:name AS string)) || '%')
            AND (:hasMedicalShift IS NULL OR (:hasMedicalShift = FALSE) OR (:hasMedicalShift = TRUE AND ms IS NOT NULL))
            AND (:hasPendingVaccine IS NULL OR (:hasPendingVaccine = FALSE) OR (:hasPendingVaccine = TRUE AND vac.completed = FALSE))
    """)
    fun getThisVetsPetFilter(
        @Param("idVet") idVet: Int,
        @Param("name") name: String?,
        @Param("hasMedicalShift") hasMedicalShift: Boolean?,
        @Param("hasPendingVaccine") hasPendingVaccine: Boolean?
    ): List<Pet>
}
