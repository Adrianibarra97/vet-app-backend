package ar.edu.unsam.proyecto.vetappbackend.service.user
import jakarta.transaction.*
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import ar.edu.unsam.proyecto.vetappbackend.domain.shift.*
import ar.edu.unsam.proyecto.vetappbackend.dto.filter.FilterPet
import ar.edu.unsam.proyecto.vetappbackend.dto.filter.MedicalShiftFilter

import ar.edu.unsam.proyecto.vetappbackend.service.*
import ar.edu.unsam.proyecto.vetappbackend.service.pet.*
import ar.edu.unsam.proyecto.vetappbackend.service.shift.*

import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.repository.user.PetOwnerRepository


@Service
class PetOwnerService : BaseService<PetOwner> {

    @Autowired lateinit var petOwnerRepository: PetOwnerRepository

    @Autowired private lateinit var authCredentialsService: AuthCredentialsService

    @Autowired lateinit var medicalShiftService: MedicalShiftService

    @Autowired lateinit var petService: PetService

    override fun getOneById(idPetOwner: Int): PetOwner {
        return this.petOwnerRepository.findById(idPetOwner).orElseThrow {
            NotFoundException("No se encontró al petOwner indicado: $idPetOwner")
        }
    }

    override fun getAll(): List<PetOwner> {
        return this.petOwnerRepository.findAll().toList()
    }

    @Transactional
    override fun delete(petOwnerDelete: PetOwner) {
        this.petOwnerRepository.delete(petOwnerDelete)
    }

    @Transactional
    override fun create(newPetOwner: PetOwner) {
        this.authCredentialsService.verifyCreate(newPetOwner.authCredentials)
        this.petOwnerRepository.save(newPetOwner)
    }

    @Transactional
    override fun update(petOwnerUpdate: PetOwner) {
        this.authCredentialsService.verifyUpdate(petOwnerUpdate.authCredentials)
        this.getOneById(petOwnerUpdate.id)
        this.petOwnerRepository.save(petOwnerUpdate)
    }

    fun getAllPets(idPetOwner: Int): List<Pet> {
        val petOwner: PetOwner = this.findByAuthCredentialsId(idPetOwner)
        return this.petService.getAllThisOwnersPet(petOwner.id)
    }

    fun getAllPetsFilter(filterPet: FilterPet, idPetOwner: Int): List<Pet> {
        val petOwner: PetOwner = this.findByAuthCredentialsId(idPetOwner)
        return this.petService.getThisOwnersPetFilter(filterPet, petOwner.id)
    }

    fun getAllMedicalShiftFilter(medicalShiftFilter: MedicalShiftFilter, idPetOwner: Int): List<MedicalShift> {
        val petOwner: PetOwner = this.findByAuthCredentialsId(idPetOwner)
        return this.medicalShiftService.getMedicalShiftFilterPetOwner(medicalShiftFilter, petOwner.id)
    }

    fun findByAuthCredentialsId(idUserData: Int): PetOwner {
        return this.petOwnerRepository.findByAuthCredentialsId(idUserData).orElseThrow {
            NotFoundException("No se encontró los datos del usuario: $idUserData")
        }
    }

}