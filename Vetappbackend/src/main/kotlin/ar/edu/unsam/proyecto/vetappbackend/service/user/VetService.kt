package ar.edu.unsam.proyecto.vetappbackend.service.user
import ar.edu.unsam.proyecto.vetappbackend.domain.notification.Notification
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import ar.edu.unsam.proyecto.vetappbackend.dto.filter.*
import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.repository.user.VetRepository
import ar.edu.unsam.proyecto.vetappbackend.service.BaseService
import ar.edu.unsam.proyecto.vetappbackend.service.notification.NotificationService
import ar.edu.unsam.proyecto.vetappbackend.service.pet.PetService

@Service
class VetService: BaseService<Vet> {

    @Autowired lateinit var authCredentialsService: AuthCredentialsService

    @Autowired lateinit var medicalShiftService: MedicalShiftService

    @Autowired lateinit var notificationService: NotificationService

    @Autowired lateinit var petService: PetService

    @Autowired lateinit var vetRepository: VetRepository


    override fun getOneById(idVet: Int): Vet {
        return this.vetRepository.findById(idVet).orElseThrow {
            NotFoundException("No se encontró al veterinario indicado: $idVet")
        }
    }

    override fun getAll(): List<Vet> {
        return this.vetRepository.findAll().toList()
    }

    override fun delete(vetDelete: Vet) {
        this.vetRepository.delete(vetDelete)
    }

    override fun create(newVet: Vet) {
        this.authCredentialsService.verifyUsernameCreate(newVet.authCredentials.username!!)
        this.vetRepository.save(newVet)
    }

    @Transactional
    override fun update(vetUpdate: Vet) {
        this.authCredentialsService.verifyUsernameUpdate(vetUpdate.authCredentials.id, vetUpdate.authCredentials.username!!)
        this.getOneById(vetUpdate.id)
        this.vetRepository.save(vetUpdate)
    }


    fun getAllPets(idVet: Int): List<Pet> {
        val vet: Vet = this.findByAuthCredentialsId(idVet)
        return this.petService.getAllThisVetPet(vet.id)
    }

    fun getAllPetsFilter(filterPet: FilterPet, idVet: Int): List<Pet> {
        val vet: Vet = this.findByAuthCredentialsId(idVet)
        return petService.getThisVetsPetFilter(filterPet, vet.id)
    }

    fun getAllMedicalShiftFilter(medicalShiftFilter: MedicalShiftFilter, idVet: Int): List<MedicalShift> {
        val vet: Vet = this.findByAuthCredentialsId(idVet)
        return this.medicalShiftService.getMedicalShiftFilterVet(medicalShiftFilter, vet.id)
    }

    fun getAllNotifications(idVet: Int): List<Notification> {
        val vet: Vet = this.findByAuthCredentialsId(idVet)
        return this.notificationService.getAllNotificationsVet(vet.id)
    }

    fun findByAuthCredentialsId(idAuthCredentials: Int): Vet {
        return this.vetRepository.findByAuthCredentialsId(idAuthCredentials).orElseThrow {
            NotFoundException("No se encontró los datos del usuario: $idAuthCredentials")
        }
    }

}