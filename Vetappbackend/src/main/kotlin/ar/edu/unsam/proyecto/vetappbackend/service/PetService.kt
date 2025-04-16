package ar.edu.unsam.proyecto.vetappbackend.service

import ar.edu.unsam.proyecto.vetappbackend.domain.Pet
import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.repository.MedicalShiftRepository
import ar.edu.unsam.proyecto.vetappbackend.repository.PetRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class PetService : BaseService<Pet> {

    @Autowired lateinit var petRepository: PetRepository
    @Autowired lateinit var medicalShiftRepository: MedicalShiftRepository

    override fun getAll(): List<Pet> {
        // Utiliza el método findAll() proporcionado por CrudRepository
        return this.petRepository.findAll().toList()
    }

    override fun getOneById(idPet: Int): Pet {
        // Usa findById(), que devuelve un Optional, y lanza una excepción personalizada si no encuentra la entidad
        return this.petRepository.findById(idPet).orElseThrow {
            NotFoundException("No se encontró la mascota indicada: $idPet")
        }
    }

    @Transactional
    override fun update(petUpdate: Pet) {
        // Verifica primero si la entidad existe
        val pet: Pet = this.getOneById(petUpdate.id!!)
        pet.apply {
            this.id = petUpdate.id
            this.name = petUpdate.name
            this.age = petUpdate.age
            this.birth = petUpdate.birth
            this.sex = petUpdate.sex
            this.breed = petUpdate.breed
            this.specie = petUpdate.specie
            this.weight = petUpdate.weight
            this.sterilized = petUpdate.sterilized
            this.photo = petUpdate.photo
            this.medicalHistory = petUpdate.medicalHistory
        }
        // Save es suficiente aquí, ya que CrudRepository se encarga de actualizar la entidad si ya existe
        this.petRepository.save(pet)
    }

    override fun delete(petDelete: Pet) {
        // Usa delete() directamente
        this.petRepository.delete(petDelete)
    }

    override fun create(newPet: Pet) {
        // Usa save() para guardar una nueva entidad
        this.petRepository.save(newPet)
    }

    fun getAllByName(name: String): List<Pet> {
        // Usa la función personalizada definida en el repositorio
        return this.petRepository.findByNameContainingIgnoreCase(name)
    }

    fun getAllByShiftToday(date: LocalDate): List<Pet> {
        // Usa la función personalizada definida en el repositorio
        return this.medicalShiftRepository.findByDate(date).mapNotNull { it.patient }.distinctBy { it.id }
    }

    fun getAllPendingVaccines(pendingVaccine: Boolean): List<Pet> {
        return if (pendingVaccine) {
            // Mascotas con vacunas pendientes
            this.petRepository.findPetsWithPendingVaccines()
        } else {
            // Mascotas con todas las vacunas completadas
            this.petRepository.findPetsWithCompletedVaccines()
        }
    }
}