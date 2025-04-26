package ar.edu.unsam.proyecto.vetappbackend.service

import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.repository.PetRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import jakarta.transaction.Transactional

@Service
class PetService : BaseService<Pet> {

    @Autowired lateinit var petRepository: PetRepository

    override fun getAll(): List<Pet> {
        return this.petRepository.findAll().toList()
    }

    override fun create(newPet: Pet) {
        this.petRepository.save(newPet)
    }

    @Transactional
    override fun delete(petDelete: Pet) {
        petRepository.delete(petDelete)
    }


    override fun getOneById(idPet: Int): Pet {
        return this.petRepository.findById(idPet).orElseThrow {
            NotFoundException("No se encontró la mascota indicada: $idPet")
        }
    }

    @Transactional
    override fun update(petUpdate: Pet) {
        val pet: Pet = this.getOneById(petUpdate.id!!)
        pet.apply {
            this.id = petUpdate.id
            this.age = petUpdate.age
            this.name = petUpdate.name
            this.photo = petUpdate.photo
            this.breed = petUpdate.breed
            this.weight = petUpdate.weight
            this.birth = petUpdate.birth
            this.sex = petUpdate.sex
            this.specie = petUpdate.specie
        }
        this.petRepository.save(pet)
    }

}