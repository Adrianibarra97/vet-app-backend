package ar.edu.unsam.proyecto.vetappbackend.controller

import org.springframework.beans.factory.annotation.Autowired
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.service.*
import ar.edu.unsam.proyecto.vetappbackend.dto.*
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
@RequestMapping("/pet")
class PetController {
    @Autowired private lateinit var petService: PetService

    @GetMapping("/get-all")
    fun getAll(): List<PetDTO> {
        return this.petService.getAll().map { it.toDTO() }
    }

    @PostMapping("/create")
    fun create(@RequestBody newPetDTO: PetDTO) {
      this.petService.create(newPetDTO.fromJSON(newPetDTO))
    }

    @PutMapping("/update")
    fun update(@RequestBody newPetDTO: PetDTO) {
        this.petService.update(newPetDTO.fromJSON(newPetDTO))
    }

    @DeleteMapping("delete")
    fun delete(@RequestParam idPet: Int) {
        val petForDelete: Pet = this.petService.getOneById(idPet)
        this.petService.delete(petForDelete)
    }

}