package ar.edu.unsam.proyecto.vetappbackend.service


import ar.edu.unsam.proyecto.vetappbackend.domain.Pet
import ar.edu.unsam.proyecto.vetappbackend.domain.PetFilter
import ar.edu.unsam.proyecto.vetappbackend.domain.Vet
import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.repository.VetRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class VetService: BaseService<Vet> {

    @Autowired
    lateinit var vetRepository: VetRepository

    override fun getAll(): List<Vet> {
        // Utiliza el método findAll() proporcionado por CrudRepository
        return this.vetRepository.findAll().toList()
    }

    override fun getOneById(idVet: Int): Vet {
        return this.vetRepository.findById(idVet).orElseThrow {
            NotFoundException("No se encontró al veterinario indicado: $idVet")
        }
    }

    fun getAllPets(idVet: Int): List<Pet> {
        val vet: Vet = this.getOneById(idVet)
        return this.vetRepository.findAllPetsByVetId(vet.id!!)
    }

    override fun create(newVet: Vet) {
        this.vetRepository.save(newVet)
    }

    override fun delete(vetDelete: Vet) {
        this.vetRepository.delete(vetDelete)
    }

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

    fun getAllByFilter(petFilter: PetFilter, vettId: Int): List<Pet> {
        val vet: Vet = this.getOneById(vettId)

        return vetRepository.getAllByFilter(
            vet.id!!,
            petFilter.name,
            petFilter.shiftDate
            //petFilter.pendingVaccine
        )
    }
}