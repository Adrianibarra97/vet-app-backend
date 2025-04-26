package ar.edu.unsam.proyecto.vetappbackend.repository

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
    WHERE ms.vet.id = :idVet
        AND ms.date = COALESCE(:day, ms.date)
        AND ms.date >= COALESCE(:beginingOfWeek, ms.date)
        AND ms.date <= COALESCE(:endingOfWeek, ms.date)
        AND ms.date = COALESCE(:today, ms.date)
    """)
    fun getAllByFilterMedicalShift(
        @Param("idVet") idVet: Int,
        @Param("day") day: LocalDate?,
        @Param("today") today: LocalDate?,
        @Param("beginingOfWeek") beginingOfWeek: LocalDate?,
        @Param("endingOfWeek") endingOfWeek: LocalDate?
    ): List<MedicalShift>


}