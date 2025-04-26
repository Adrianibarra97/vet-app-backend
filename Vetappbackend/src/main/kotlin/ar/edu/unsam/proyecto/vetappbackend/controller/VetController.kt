package ar.edu.unsam.proyecto.vetappbackend.controller

import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import ar.edu.unsam.proyecto.vetappbackend.dto.*
import ar.edu.unsam.proyecto.vetappbackend.service.VetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

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
        return this.vetService.getOneById(idVet).toDTO()
    }

    @PostMapping("/create-vet")
    fun create(@RequestBody vetDTO: VetDTO) {
        this.vetService.create(vetDTO.fromJSON(vetDTO))
    }

    @PutMapping("update-vet")
    fun update(@RequestBody vetDTO: VetDTO) {
         vetService.update(vetDTO.fromJSON(vetDTO))
    }

    @DeleteMapping("delete-vet")
    fun delete(@RequestParam id: Int) {
        val vetForDelete: Vet = this.vetService.getOneById(id)
        this.vetService.delete(vetForDelete)
    }

    @GetMapping("/get-all-pets")
    fun getAllPets(@RequestParam idVet: Int): List<PetDTO> {
        return this.vetService.getAllPets(idVet).map { it.toDTO() }
    }

    @PostMapping("/get-all-pets-by-filter-vet")
    fun getAllByFilter(@RequestBody vetFilterPetDTO: VetFilterPetDTO, @RequestParam vetId: Int): List<PetDTO> {
        val vetFilterPet: VetFilterPet = vetFilterPetDTO.fromJSON(vetFilterPetDTO)
        return vetService.getAllPetsFilter(vetFilterPet, vetId).map { it.toDTO() }
    }

}