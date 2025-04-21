package ar.edu.unsam.proyecto.vetappbackend.service

import ar.edu.unsam.proyecto.vetappbackend.domain.Pet
import ar.edu.unsam.proyecto.vetappbackend.domain.PetOwner
import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.repository.PetOwnerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PetOwnerService : BaseService<PetOwner> {

    @Autowired
    lateinit var petOwnerRepository: PetOwnerRepository

    override fun getAll(): List<PetOwner> {
        // Utiliza el método findAll() proporcionado por CrudRepository
        return this.petOwnerRepository.findAll().toList()
    }

    override fun getOneById(idPetOwner: Int): PetOwner {
        return this.petOwnerRepository.findById(idPetOwner).orElseThrow {
            NotFoundException("No se encontró al veterinario indicado: $idPetOwner")
        }
    }

    fun getAllPets(idPetOwner: Int): List<Pet> {
        val petOwner: PetOwner = this.getOneById(idPetOwner)
        return this.petOwnerRepository.findAllPetsByOwnerId(petOwner.id!!)
    }

    override fun create(newPetOwner: PetOwner) {
        this.petOwnerRepository.save(newPetOwner)
    }

    override fun delete(petOwnerDelete: PetOwner) {
        this.petOwnerRepository.delete(petOwnerDelete)
    }

    override fun update(petOwnerUpdate: PetOwner) {
        val petOwner: PetOwner = getOneById(petOwnerUpdate.id!!)
        petOwner.apply {
            this.id = petOwnerUpdate.id
            this.name = petOwnerUpdate.name
            this.surname = petOwnerUpdate.surname
            this.username = petOwnerUpdate.username
            this.password = petOwnerUpdate.password
            this.dni = petOwnerUpdate.dni
            this.email = petOwnerUpdate.email
            this.telephone = petOwnerUpdate.telephone
            this.address = petOwnerUpdate.address
        }
        this.petOwnerRepository.save(petOwner)
    }
}