package ar.edu.unsam.proyecto.vetappbackend.service

import ar.edu.unsam.proyecto.vetappbackend.domain.Pet
import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.repository.PetRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class PetService: BaseService<Pet> {

    @Autowired
    lateinit var petRepository: PetRepository


    override fun getAll(): List<Pet> {
        return this.petRepository.getAll().toList()
    }


    private fun checkIdPet(idPet: Int): Pet {
        return this.petRepository.findById(idPet)
            ?: throw NotFoundException("No se encontró el chofer indicado: $idPet")
    }

    override fun update(petUpdate: Pet) {
        val pet: Pet = checkIdPet(petUpdate.id)
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
            this.medicalShift = petUpdate.medicalShift
            this.pendingVaccines = petUpdate.pendingVaccines
        }
        this.petRepository.update(pet)
    }

    override fun create(newPet: Pet) {
        this.petRepository.save(newPet)
    }

    override fun getOneById(objectId: Int): Pet {
        TODO("Not yet implemented")
    }

    override fun delete(anObject: Pet) {
        TODO("Not yet implemented")
    }


    fun getAllByName(name: String): List<Pet> =
        this.petRepository.getAllByName(name)

    fun getAllByShiftToday(date: LocalDate): List<Pet> {
        return petRepository.getAllByShiftToday(date)
    }

    fun getAllPendingVaccines(pendingVaccine: Boolean): List<Pet> {
        return petRepository.getAllPendingVaccines(pendingVaccine)
    }

}