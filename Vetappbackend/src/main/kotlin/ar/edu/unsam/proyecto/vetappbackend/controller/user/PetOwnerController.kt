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
@RequestMapping("/pet-owner")
class PetOwnerController {

    @Autowired private lateinit var petOwnerService: PetOwnerService

    @GetMapping("/get-all")
    fun getAll(): List<PetOwnerDTO> {
        return this.petOwnerService.getAll().map { it.toDTO() }
    }

    @GetMapping("/get-one-by-id")
    fun getOneById(idPet: Int): PetOwnerDTO {
        return this.petOwnerService.getOneById(idPet).toDTO()
    }

    @PostMapping("/create")
    fun create(@RequestBody petOwnerDTO: PetOwnerDTO) {
        this.petOwnerService.create(petOwnerDTO.fromJSON(petOwnerDTO))
    }

    @PutMapping("/update")
    fun update(@RequestBody petOwnerDTO: PetOwnerDTO) {
        petOwnerService.update(petOwnerDTO.fromJSON(petOwnerDTO))
    }

    @DeleteMapping("/delete")
    fun delete(@RequestParam idPetOwner: Int) {
        val petOwnerForDelete: PetOwner = this.petOwnerService.getOneById(idPetOwner)
        this.petOwnerService.delete(petOwnerForDelete)
    }

    @GetMapping("/get-all-pets")
    fun getAllPets(@RequestParam idPetOwner: Int): List<PetDTO> {
        return this.petOwnerService.getAllPets(idPetOwner).map { it.toDTO() }
    }

    @PostMapping("/get-all-pets-by-filter")
    fun getAllByFilter(@RequestBody filterPetDTO: FilterPetDTO, @RequestParam idPetOwner: Int): List<PetDTO> {
        val filterPet: FilterPet = filterPetDTO.fromJSON(filterPetDTO)
        return petOwnerService.getAllPetsFilter(filterPet, idPetOwner).map { it.toDTO() }
    }

    @PostMapping("/get-all-medical-shift-by-filter-pet-owner")
    fun getAllByFilterMedicalShift(@RequestBody medicalShiftFilterDTO: MedicalShiftFilterDTO, @RequestParam petOwnerId: Int): List<MedicalShiftResponseDTO> {
        val medicalShiftFilter: MedicalShiftFilter = medicalShiftFilterDTO.fromJSON(medicalShiftFilterDTO,)
        return petOwnerService.getAllMedicalShiftFilter(medicalShiftFilter, petOwnerId).map { it.toDTO() }
    }

}