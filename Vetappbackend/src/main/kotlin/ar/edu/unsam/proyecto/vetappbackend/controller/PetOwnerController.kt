package ar.edu.unsam.proyecto.vetappbackend.controller


import ar.edu.unsam.proyecto.vetappbackend.domain.PetOwner
import ar.edu.unsam.proyecto.vetappbackend.dto.*
import ar.edu.unsam.proyecto.vetappbackend.service.PetOwnerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@CrossOrigin("*")
class PetOwnerController {

    @Autowired
    private lateinit var petOwnerService: PetOwnerService

    @GetMapping("/pet-owner/get-all")
    fun getAll(): List<PetOwnerDTO> {
        return this.petOwnerService.getAll().map { it.toJSON() }
    }

    @GetMapping("/pet-owner/get-one-by-id/{id}")
    fun getOneById(@PathVariable id: Int): PetOwnerDTO {
        return this.petOwnerService.getOneById(id).toJSON()
    }

    @GetMapping("/pet-owner/get-all-pets/{id}")
    fun getAllPets(@PathVariable id: Int): List<PetDTO> {
        return this.petOwnerService.getAllPets(id).map { it.toJSON() }
    }

    @PutMapping("/pet-owner/update/{id}")
    fun update(@RequestBody petOwnerDTO: PetOwnerDTO) {
        petOwnerService.update(petOwnerDTO.fromJSON(petOwnerDTO))
    }

    @DeleteMapping("/pet-owner/delete/{id}")
    fun delete(@PathVariable id: Int) {
        val petOwnerForDelete: PetOwner = this.petOwnerService.getOneById(id)
        this.petOwnerService.delete(petOwnerForDelete)
    }

    @PostMapping("/pet-owner/create-pet-owner")
    fun create(@RequestBody petOwnerDTO: PetOwnerDTO) {
        this.petOwnerService.create(petOwnerDTO.fromJSON(petOwnerDTO))
    }

    @PostMapping("/pet-owner/filter/get-all-by-filter/{petOwnerId}")
    fun getAllByFilter(@RequestBody petOwnerFilterPetDTO: PetOwnerFilterPetDTO, @PathVariable petOwnerId: Int): List<PetDTO> {
        val petOwnerFilterPet: PetOwnerFilterPet = petOwnerFilterPetDTO.fromJSON(petOwnerFilterPetDTO)
        return petOwnerService.getAllPetsFilter(petOwnerFilterPet, petOwnerId).map { it.toJSON() }
    }
}