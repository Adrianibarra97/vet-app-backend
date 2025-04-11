package ar.edu.unsam.proyecto.vetappbackend.repository

import ar.edu.unsam.proyecto.vetappbackend.domain.MedicalShift
import ar.edu.unsam.proyecto.vetappbackend.domain.Pet
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class PetRepository {

    private val pets = mutableListOf<Pet>()

    private val medicalShifts = mutableListOf<MedicalShift>()

    fun getAll(): List<Pet> {
        return pets
    }

    fun getAllByName(name: String): List<Pet> {
        return pets.filter { it.name.contains(name, true) }
    }

    fun save(pet: Pet) {
        pets.add(pet)
    }

    fun saveMedicalShift(medicalShift: MedicalShift) {
        medicalShifts.add(medicalShift)
    }

    fun saveMedicalShifts(medicalShiftsToAdd: List<MedicalShift>) {
        medicalShifts.addAll(medicalShiftsToAdd)
    }

    fun saveAll(petsToAdd: List<Pet>) {
        pets.addAll(petsToAdd)
    }

    fun getAllByShiftToday(appointmentDate: LocalDate): List<Pet> {
        return medicalShifts.filter { it.date.toLocalDate().isEqual(appointmentDate) }
            .map { it.patient }
    }
}
