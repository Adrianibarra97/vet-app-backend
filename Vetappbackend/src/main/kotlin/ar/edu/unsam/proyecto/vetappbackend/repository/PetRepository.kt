package ar.edu.unsam.proyecto.vetappbackend.repository

import ar.edu.unsam.proyecto.vetappbackend.domain.Pet
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface PetRepository : CrudRepository<Pet, Int> {

    // Encuentra todas las mascotas con un nombre que contiene el texto especificado, ignorando mayúsculas/minúsculas
    fun findByNameContainingIgnoreCase(name: String): List<Pet>

    // Encuentra todas las mascotas asociadas a turnos en una fecha específica
    //fun findByMedicalHistory_MedicalShifts_Date(date: LocalDate): List<Pet>

    // Encuentra mascotas con todas sus vacunas pendientes
    @Query( """ SELECT DISTINCT p FROM Pet p JOIN p.medicalHistory mh 
            JOIN mh.vaccines v WHERE v.completed = false """
    )
    fun findPetsWithPendingVaccines(): List<Pet>

    // Encuentra mascotas con todas sus vacunas completadas
    @Query( """ SELECT p FROM Pet p JOIN p.medicalHistory mh 
            LEFT JOIN mh.vaccines v GROUP BY p.id, mh.id 
            HAVING SUM(CASE WHEN v.completed = false THEN 1 ELSE 0 END) = 0 """
    )
    fun findPetsWithCompletedVaccines(): List<Pet>
}