package ar.edu.unsam.proyecto.vetappbackend.controller.pet
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.*
import ar.edu.unsam.proyecto.vetappbackend.dto.pet.*
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.domain.user.PetOwner
import ar.edu.unsam.proyecto.vetappbackend.dto.filter.FilterPet
import ar.edu.unsam.proyecto.vetappbackend.dto.filter.FilterPetDTO
import ar.edu.unsam.proyecto.vetappbackend.dto.filter.fromJSON
import ar.edu.unsam.proyecto.vetappbackend.service.pet.*
import ar.edu.unsam.proyecto.vetappbackend.service.user.PetOwnerService

@RestController
@CrossOrigin("*")
@RequestMapping("/pet")
class PetController {

    @Autowired private lateinit var petOwnerService: PetOwnerService

    @Autowired private lateinit var petService: PetService

    @GetMapping("/get-all")
    fun getAll(): List<PetDTO> {
        return this.petService.getAll().map { it.toDTO() }
    }

    @GetMapping("/get-one-by-id")
    fun getOneById(@RequestParam idPet: Int): PetDTO {
        return this.petService.getOneById(idPet).toDTO()
    }

    @DeleteMapping("delete")
    fun delete(@RequestParam idPet: Int) {
        val petForDelete: Pet = this.petService.getOneById(idPet)
        this.petService.delete(petForDelete)
    }

    @PostMapping("/create")
    fun create(@RequestBody petDTO: PetDTO, @RequestParam idPetOwner: Int) {
        val petOwner: PetOwner = this.petOwnerService.getOneById(idPetOwner)
        this.petService.create(petDTO.fromJSON(petOwner))
    }

    @PutMapping("/update")
    fun update(@RequestBody petDTO: PetDTO, @RequestParam idPetOwner: Int) {
        val petOwner: PetOwner = this.petOwnerService.getOneById(idPetOwner)
        this.petService.update(petDTO.fromJSON(petOwner))
    }


    @GetMapping("/get-all-pets/pet-owner")
    fun getAllPetsPetOwner(@RequestParam idPetOwner: Int): List<PetDTO> {
        return this.petService.getAllThisOwnersPet(idPetOwner).map { it.toDTO() }
    }

    @GetMapping("/get-all-pets/vet")
    fun getAllPetsVet(@RequestParam idVet: Int): List<PetDTO> {
        return this.petService.getAllThisVetPet(idVet).map { it.toDTO() }
    }

    @PostMapping("/get-all-pets-by-filter/pet-owner")
    fun getAllByFilterPetOwner(@RequestBody filterPetDTO: FilterPetDTO, @RequestParam idPetOwner: Int): List<PetDTO> {
        val filterPet: FilterPet = filterPetDTO.fromJSON()
        return this.petService.getThisOwnersPetFilter(filterPet, idPetOwner).map { it.toDTO() }
    }

    @PostMapping("/get-all-pets-by-filter/vet")
    fun getAllByFilterVet(@RequestBody filterPetDTO: FilterPetDTO, @RequestParam idVet: Int): List<PetDTO> {
        val filterPet: FilterPet = filterPetDTO.fromJSON()
        return this.petService.getThisVetsPetFilter(filterPet, idVet).map { it.toDTO() }
    }

}