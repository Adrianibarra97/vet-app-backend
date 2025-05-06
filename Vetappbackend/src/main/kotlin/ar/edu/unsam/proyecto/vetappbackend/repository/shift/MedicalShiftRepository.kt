package ar.edu.unsam.proyecto.vetappbackend.repository.shift

import ar.edu.unsam.proyecto.vetappbackend.domain.shift.MedicalShift
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface MedicalShiftRepository: CrudRepository<MedicalShift, Int> {
    @Query("""
    SELECT ms FROM MedicalShift ms 
    WHERE ms.vet.id = :idVet AND (
        (COALESCE(:day, NULL) IS NOT NULL AND ms.date = :day) OR
        (COALESCE(:today, NULL) IS NOT NULL AND ms.date = :today) OR
        (COALESCE(:beginningOfWeek, NULL) IS NOT NULL AND COALESCE(:endingOfWeek, NULL) IS NOT NULL AND ms.date BETWEEN :beginningOfWeek AND :endingOfWeek) OR
        (COALESCE(:day, NULL) IS NULL AND COALESCE(:today, NULL) IS NULL AND COALESCE(:beginningOfWeek, NULL) IS NULL AND COALESCE(:endingOfWeek, NULL) IS NULL)
        ) 
    """)
    fun getAllByFilterMedicalShiftVet(
        @Param("idVet") idVet: Int,
        @Param("day") day: LocalDate?,
        @Param("today") today: LocalDate?,
        @Param("beginningOfWeek") beginningOfWeek: LocalDate?,
        @Param("endingOfWeek") endingOfWeek: LocalDate?
    ): List<MedicalShift>


    @Query("""
    SELECT ms FROM MedicalShift ms
    WHERE ms.pet.petOwner.id = :petOwnerId AND (
        (COALESCE(:day, NULL) IS NOT NULL AND ms.date = :day) OR
        (COALESCE(:today, NULL) IS NOT NULL AND ms.date = :today) OR
        (COALESCE(:beginningOfWeek, NULL) IS NOT NULL AND COALESCE(:endingOfWeek, NULL) IS NOT NULL AND ms.date BETWEEN :beginningOfWeek AND :endingOfWeek) OR
        (COALESCE(:day, NULL) IS NULL AND COALESCE(:today, NULL) IS NULL AND COALESCE(:beginningOfWeek, NULL) IS NULL AND COALESCE(:endingOfWeek, NULL) IS NULL)
        )
    """)
    fun getAllByFilterMedicalShiftPetOwner(
        @Param("petOwnerId") petOwnerId: Int,
        @Param("day") day: LocalDate?,
        @Param("today") today: LocalDate?,
        @Param("beginningOfWeek") beginningOfWeek: LocalDate?,
        @Param("endingOfWeek") endingOfWeek: LocalDate?
    ): List<MedicalShift>

}

// Possible combinations for filtering medical appointments
// { "day": null, "today": false, "thisWeek": false }
// { "day": null, "today": false, "thisWeek": true }
// { "day": null, "today": true, "thisWeek": false }
// { "day": null, "today": true, "thisWeek": true }
// { "day": "2025-04-26", "today": false, "thisWeek": false }
// { "day": "2025-04-26", "today": false, "thisWeek": true }
// { "day": "2025-04-26", "today": true, "thisWeek": false }
// { "day": "2025-04-26", "today": true, "thisWeek": true } ó { "day": "2025-06-26", "today": true, "thisWeek": true }