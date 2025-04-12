package ar.edu.unsam.proyecto.vetappbackend.repository

import ar.edu.unsam.proyecto.vetappbackend.domain.MedicalShift
import ar.edu.unsam.proyecto.vetappbackend.domain.Pet
import ar.edu.unsam.proyecto.vetappbackend.domain.Vaccine
import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class PetRepository {

    private val pets = mutableListOf<Pet>()
    private val medicalShifts = mutableListOf<MedicalShift>()
    private val pendingVaccines = mutableListOf<Vaccine>()

    fun getAll(): List<Pet> {
        return pets
    }

    fun getAllByName(name: String): List<Pet> {
        return pets.filter { it.name.contains(name, true) }
    }

    fun findById(id: Int): Pet? {
        return pets.find { it.id == id }
    }

    fun update(pet: Pet) {
        pets[pet.id] = pet
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
        return medicalShifts.filter { it.date!!.isEqual(appointmentDate) }
            .map { it.patient }
    }

    fun getAllPendingVaccines(pendingVaccine: Boolean): List<Pet> {
        return pets.filter { pet ->
            if (pendingVaccine) pet.pendingVaccines.any { !it.completed } //Verifica si la mascota tiene al menos una vacuna pendiente
            else pet.pendingVaccines.all { it.completed } //Verifica si todas las vacunas de las mascotas están completedas usando all.
        }
    }


}
