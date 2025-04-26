package ar.edu.unsam.proyecto.vetappbackend.service

import ar.edu.unsam.proyecto.vetappbackend.repository.*
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.error.*
import ar.edu.unsam.proyecto.vetappbackend.dto.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PetOwnerService : BaseService<PetOwner> {
    @Autowired lateinit var petOwnerRepository: PetOwnerRepository

    override fun getAll(): List<PetOwner> {
        return this.petOwnerRepository.findAll().toList()
    }

    override fun create(newPetOwner: PetOwner) {
        this.petOwnerRepository.save(newPetOwner)
    }

    override fun delete(petOwnerDelete: PetOwner) {
        this.petOwnerRepository.delete(petOwnerDelete)
    }

    override fun getOneById(idPetOwner: Int): PetOwner {
        return this.petOwnerRepository.findById(idPetOwner).orElseThrow {
            NotFoundException("No se encontró al petOwner indicado: $idPetOwner")
        }
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

    fun getAllPets(idPetOwner: Int): List<Pet> {
        val petOwner: PetOwner = this.getOneById(idPetOwner)
        return this.petOwnerRepository.findAllPetsByOwnerId(petOwner.id!!)
    }

    fun getAllPetsFilter(petOwnerFilterPet: FilterPet, petOwnerId: Int): List<Pet> {
        val petOwner: PetOwner = this.getOneById(petOwnerId)
        return petOwnerRepository.getAllByFilter(
            petOwner.id!!,
            petOwnerFilterPet.name,
            petOwnerFilterPet.hasMedicalShift,
            petOwnerFilterPet.hasPendingVaccine
        )
    }

}