package ar.edu.unsam.proyecto.vetappbackend.service.pet

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.Pet
import ar.edu.unsam.proyecto.vetappbackend.dto.filter.FilterPet
import ar.edu.unsam.proyecto.vetappbackend.service.BaseService
import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.repository.pet.PetRepository

@Service
class PetService : BaseService<Pet> {

    @Autowired lateinit var petRepository: PetRepository

    override fun getOneById(idPet: Int): Pet {
        return this.petRepository.findById(idPet).orElseThrow {
            NotFoundException("No se encontró la mascota indicada: $idPet")
        }
    }

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

    @Transactional
    override fun update(petUpdate: Pet) {
        this.getOneById(petUpdate.id!!)
        this.petRepository.save(petUpdate)
    }

    fun getAllThisOwnersPet(idPetOwner: Int): List<Pet> {
        return this.petRepository.findAllPetsByOwnerId(idPetOwner)
    }

    fun getThisOwnersPetFilter(filterPet: FilterPet, idPetOwner: Int): List<Pet> {
        return this.petRepository.getThisOwnersPetFilter(
            idPetOwner,
            filterPet.name,
            filterPet.hasMedicalShift,
            filterPet.hasPendingVaccine
        )
    }

    fun getThisVetsPetFilter(filterPet: FilterPet, idVet: Int): List<Pet> {
        return this.petRepository.getThisVetsPetFilter(
            idVet,
            filterPet.name,
            filterPet.hasMedicalShift,
            filterPet.hasPendingVaccine
        )
    }

}