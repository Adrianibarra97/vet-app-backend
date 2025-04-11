package ar.edu.unsam.proyecto.vetappbackend.repository

import ar.edu.unsam.proyecto.vetappbackend.domain.MedicalShift
import ar.edu.unsam.proyecto.vetappbackend.domain.Pet
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class PetRepository {

    private val pets = mutableListOf<Pet>()

    fun getAll(): List<Pet> {
        return pets
    }

    fun getAllByName(name: String): List<Pet> {
        return pets.filter { it.name.contains(name, true) }
    }

    fun save(pet: Pet) {
        pets.add(pet)
    }

    fun saveAll(petsToAdd: List<Pet>) {
        pets.addAll(petsToAdd)
    }
}


@Repository
class MedicalShiftRepository {

    private val shifts = mutableListOf<MedicalShift>()

    fun save(shift: MedicalShift) {
        shifts.add(shift)
    }

    fun saveAll(shiftList: List<MedicalShift>) {
        shifts.addAll(shiftList)
    }

    fun getAllByShiftToday(appointmentDate: LocalDate): List<Pet> {
        return shifts.filter { it.date.toLocalDate().isEqual(appointmentDate) }
            .map { it.patient }
    }
}