package ar.edu.unsam.proyecto.vetappbackend.repository

import ar.edu.unsam.proyecto.vetappbackend.domain.shift.MedicalShift
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface MedicalShiftRepository: CrudRepository<MedicalShift, Int> {
    fun findByDate(date: LocalDate): List<MedicalShift>
}