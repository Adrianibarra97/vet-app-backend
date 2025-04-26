package ar.edu.unsam.proyecto.vetappbackend.service
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.domain.shift.MedicalShift
import ar.edu.unsam.proyecto.vetappbackend.error.*
import ar.edu.unsam.proyecto.vetappbackend.dto.*

import ar.edu.unsam.proyecto.vetappbackend.repository.VetRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class VetService: BaseService<Vet> {
    @Autowired lateinit var vetRepository: VetRepository

    @Autowired lateinit var medicalShiftService: MedicalShiftService

    override fun getAll(): List<Vet> = this.vetRepository.findAll().toList()

    override fun delete(vetDelete: Vet) { this.vetRepository.delete(vetDelete) }

    override fun create(newVet: Vet) { this.vetRepository.save(newVet) }

    override fun getOneById(idVet: Int): Vet {
        return this.vetRepository.findById(idVet).orElseThrow {
            NotFoundException("No se encontró al veterinario indicado: $idVet")
        }
    }

    @Transactional
    override fun update(vetUpdate: Vet) {
        val vet: Vet = getOneById(vetUpdate.id!!)
        vet.apply {
            this.id = vetUpdate.id
            this.name = vetUpdate.name
            this.surname = vetUpdate.surname
            this.username = vetUpdate.username
            this.password = vetUpdate.password
            this.licence = vetUpdate.licence
            this.speciality = vetUpdate.speciality
            this.businessHours = vetUpdate.businessHours
            this.professionalEmail = vetUpdate.professionalEmail
            this.professionalAdress = vetUpdate.professionalAdress
            this.professionalTelephone = vetUpdate.professionalTelephone
        }
        this.vetRepository.save(vet)
    }

    fun getAllPets(idVet: Int): List<Pet> {
        val vet: Vet = this.getOneById(idVet)
        return this.vetRepository.findAllPetsByVetId(vet.id!!)
    }

    fun getAllPetsFilter(vetFilterPet: VetFilterPet, vetId: Int): List<Pet> {
        val vet: Vet = this.getOneById(vetId)
        return vetRepository.getAllByFilterPets(
            vet.id!!,
            vetFilterPet.name,
            vetFilterPet.hasMedicalShift,
            vetFilterPet.hasPendingVaccine
        )
    }

    fun getAllMedicalShiftFilter(medicalShiftFilter: MedicalShiftFilter, vetId: Int): List<MedicalShift> {
        val vet: Vet = this.getOneById(vetId)
        return this.medicalShiftService.getMedicalShiftFilterVet(medicalShiftFilter, vet.id!!)
    }

}