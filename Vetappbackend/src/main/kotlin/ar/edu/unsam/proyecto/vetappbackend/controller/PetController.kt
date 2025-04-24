package ar.edu.unsam.proyecto.vetappbackend.controller

import org.springframework.beans.factory.annotation.Autowired
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.service.*
import ar.edu.unsam.proyecto.vetappbackend.dto.*
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
class PetController {
    @Autowired private lateinit var petService: PetService

    @GetMapping("/pet/get-all")
    fun getAll(): List<PetDTO> {
        return this.petService.getAll().map { it.toDTO() }
    }

    @PostMapping("/pet/create-pet")
    fun create(@RequestBody newPetDTO: PetDTO) {
      this.petService.create(newPetDTO.fromJSON(newPetDTO))
    }

    @PutMapping("/pet/update-pet")
    fun update(@RequestBody newPetDTO: PetDTO) {
        this.petService.update(newPetDTO.fromJSON(newPetDTO))
    }

    @DeleteMapping("/pet/delete-pet")
    fun delete(@RequestParam idPet: Int) {
        val petForDelete: Pet = this.petService.getOneById(idPet)
        this.petService.delete(petForDelete)
    }

}