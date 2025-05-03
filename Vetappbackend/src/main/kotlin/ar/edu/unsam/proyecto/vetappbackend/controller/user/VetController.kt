package ar.edu.unsam.proyecto.vetappbackend.controller.user
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.*
import ar.edu.unsam.proyecto.vetappbackend.dto.pet.*
import ar.edu.unsam.proyecto.vetappbackend.dto.user.*
import ar.edu.unsam.proyecto.vetappbackend.dto.shift.*
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import ar.edu.unsam.proyecto.vetappbackend.service.user.*

@RestController
@CrossOrigin("*")
@RequestMapping("/vet")
class VetController {

    @Autowired private lateinit var vetService: VetService

    @Autowired private lateinit var userDataService: UserDataService

    @GetMapping("/get-all")
    fun getAll(): List<VetDTO> {
        return this.vetService.getAll().map { it.toDTO() }
    }

    @GetMapping("/get-one-by-id")
    fun getOneById(@RequestParam idVet: Int): VetDTO {
        return this.vetService.findByUserDataId(idVet).toDTO()
    }

    @PostMapping("/create")
    fun create(@RequestBody vetDTO: VetDTO) {
        val userData: UserData = this.userDataService.getOneById(vetDTO.idUserData)
        this.vetService.create(vetDTO.fromJSON(userData))
    }

    @PutMapping("/update")
    fun update(@RequestBody vetDTO: VetDTO) {
        val userData: UserData = this.userDataService.getOneById(vetDTO.idUserData)
        this.vetService.update(vetDTO.fromJSON(userData))
    }

    @DeleteMapping("/delete")
    fun delete(@RequestParam idVet: Int) {
        val vetForDelete: Vet = this.vetService.findByUserDataId(idVet)
        this.vetService.delete(vetForDelete)
    }

    @GetMapping("/get-all-pets")
    fun getAllPets(@RequestParam idVet: Int): List<PetDTO> {
        return this.vetService.getAllPets(idVet).map { it.toDTO() }
    }

    @PostMapping("/get-all-pets-by-filter-vet")
    fun getAllByFilterPet(@RequestBody filterPetDTO: FilterPetDTO, @RequestParam idVet: Int): List<PetDTO> {
        val filterPet: FilterPet = filterPetDTO.fromJSON()
        return vetService.getAllPetsFilter(filterPet, idVet).map { it.toDTO() }
    }

    @PostMapping("/get-all-medical-shift-by-filter-vet")
    fun getAllByFilterMedicalShift(@RequestBody medicalShiftFilterDTO: MedicalShiftFilterDTO, @RequestParam idVet: Int): List<MedicalShiftResponseDTO> {
        val medicalShiftFilter: MedicalShiftFilter = medicalShiftFilterDTO.fromJSON()
        return vetService.getAllMedicalShiftFilter(medicalShiftFilter, idVet).map { it.toDTO() }
    }

}