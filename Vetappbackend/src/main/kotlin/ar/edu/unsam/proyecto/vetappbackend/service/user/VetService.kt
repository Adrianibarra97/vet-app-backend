package ar.edu.unsam.proyecto.vetappbackend.service.user

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.Pet
import ar.edu.unsam.proyecto.vetappbackend.domain.shift.MedicalShift
import ar.edu.unsam.proyecto.vetappbackend.domain.user.Vet
import ar.edu.unsam.proyecto.vetappbackend.dto.pet.FilterPet
import ar.edu.unsam.proyecto.vetappbackend.dto.shift.MedicalShiftFilter
import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.repository.user.VetRepository
import ar.edu.unsam.proyecto.vetappbackend.service.BaseService
import ar.edu.unsam.proyecto.vetappbackend.service.pet.PetService
import ar.edu.unsam.proyecto.vetappbackend.service.shift.MedicalShiftService
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class VetService: BaseService<Vet> {

    @Autowired private lateinit var petService: PetService

    @Autowired lateinit var medicalShiftService: MedicalShiftService

    @Autowired lateinit var vetRepository: VetRepository

    override fun getOneById(idVet: Int): Vet {
        return this.vetRepository.findById(idVet).orElseThrow {
            NotFoundException("No se encontró al veterinario indicado: $idVet")
        }
    }

    fun getOneByIdLogin(idVet: Int):Vet{
        return vetRepository.findByUserDataId(idVet).orElseThrow {
            NotFoundException("No se encontró al veterinario indicado segun su id login: $idVet")
        }
    }

    override fun getAll(): List<Vet> {
        return this.vetRepository.findAll().toList()
    }

    override fun create(newVet: Vet) {
        this.vetRepository.save(newVet)
    }

    @Transactional
    override fun delete(vetDelete: Vet) {
        this.vetRepository.delete(vetDelete)
    }

    @Transactional
    override fun update(vetUpdate: Vet) {
        this.getOneById(vetUpdate.id!!)
        this.vetRepository.save(vetUpdate)
    }

    fun getAllPets(idVet: Int): List<Pet> {
        val vet: Vet = this.findByUserDataId(idVet)
        return this.vetRepository.findAllPetsByVetId(vet.id!!)
    }

    fun getAllPetsFilter(filterPet: FilterPet, idVet: Int): List<Pet> {
        val vet: Vet = this.findByUserDataId(idVet)
        return petService.getThisVetsPetFilter(filterPet, vet.id!!)
    }

    fun getAllMedicalShiftFilter(medicalShiftFilter: MedicalShiftFilter, idVet: Int): List<MedicalShift> {
        val vet: Vet = this.findByUserDataId(idVet)
        return this.medicalShiftService.getMedicalShiftFilterVet(medicalShiftFilter, vet.id!!)
    }

    fun findByUserDataId(idUserData: Int): Vet {
        return this.vetRepository.findByUserDataId(idUserData).orElseThrow {
            NotFoundException("No se encontró los datos del usuario: $idUserData")
        }
    }

}