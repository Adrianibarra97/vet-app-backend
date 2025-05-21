package ar.edu.unsam.proyecto.vetappbackend.controller.user
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.*
import ar.edu.unsam.proyecto.vetappbackend.dto.pet.*
import ar.edu.unsam.proyecto.vetappbackend.dto.user.*
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import ar.edu.unsam.proyecto.vetappbackend.dto.filter.*
import ar.edu.unsam.proyecto.vetappbackend.dto.notification.*
import ar.edu.unsam.proyecto.vetappbackend.service.user.*

@RestController
@CrossOrigin("*")
@RequestMapping("/vet")
class VetController {

    @Autowired private lateinit var vetService: VetService

    @GetMapping("/get-all")
    fun getAll(): List<VetDTO> {
        return this.vetService.getAll().map { it.toDTO() }
    }

    @GetMapping("/get-one-by-id")
    fun getOneById(@RequestParam idVet: Int): VetDTO {
        return this.vetService.findByAuthCredentialsId(idVet).toDTO()
    }

    @PostMapping("/create")
    fun create(@RequestBody vetDTO: VetDTO) {
        this.vetService.create(vetDTO.fromJSON())
    }

    @PutMapping("/update")
    fun update(@RequestBody vetDTO: VetDTO) {
        this.vetService.update(vetDTO.fromJSON())
    }

    @DeleteMapping("/delete")
    fun delete(@RequestParam idVet: Int) {
        val vetForDelete: Vet = this.vetService.findByAuthCredentialsId(idVet)
        this.vetService.delete(vetForDelete)
    }

    @GetMapping("/get-all-pets")
    fun getAllPets(@RequestParam idVet: Int): List<PetDTO> {
        return this.vetService.getAllPets(idVet).map { it.toDTO() }
    }

    @PostMapping("/get-all-pets-by-filter")
    fun getAllByFilterPet(@RequestBody filterPetDTO: FilterPetDTO, @RequestParam idVet: Int): List<PetDTO> {
        val filterPet: FilterPet = filterPetDTO.fromJSON()
        return vetService.getAllPetsFilter(filterPet, idVet).map { it.toDTO() }
    }

    @PostMapping("/get-all-medical-shift-by-filter")
    fun getAllByFilterMedicalShift(@RequestBody medicalShiftFilterDTO: MedicalShiftFilterDTO, @RequestParam idVet: Int): List<MedicalShiftResponseDTO> {
        val medicalShiftFilter: MedicalShiftFilter = medicalShiftFilterDTO.fromJSON()
        return vetService.getAllMedicalShiftFilter(medicalShiftFilter, idVet).map { it.toDTO() }
    }

    @GetMapping("/get-all-notifications")
    fun getAllNotifications(@RequestParam idVet: Int): List<NotificationResponseDTO> {
        return this.vetService.getAllNotifications(idVet).map { it.toDTO() }
    }

}