package ar.edu.unsam.proyecto.vetappbackend.controller

import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import ar.edu.unsam.proyecto.vetappbackend.dto.*
import ar.edu.unsam.proyecto.vetappbackend.service.VetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
class VetController {

    @Autowired private lateinit var vetService: VetService

    @GetMapping("/vet/get-all")
    fun getAll(): List<VetDTO> {
        return this.vetService.getAll().map { it.toDTO() }
    }

    @GetMapping("/vet/get-one-by-id/{id}")
    fun getOneById(@PathVariable id: Int): VetDTO {
        return this.vetService.getOneById(id).toDTO()
    }

    @GetMapping("/vet/get-all-pets/{id}")
    fun getAllPets(@PathVariable id: Int): List<PetDTO> {
        return this.vetService.getAllPets(id).map { it.toDTO() }
    }

    @PutMapping("/vet/update/{id}")
    fun update(@RequestBody vetDTO: VetDTO) {
         vetService.update(vetDTO.fromJSON(vetDTO))
    }

    @DeleteMapping("/vet/delete/{id}")
    fun delete(@PathVariable id: Int) {
        val vetForDelete: Vet = this.vetService.getOneById(id)
        this.vetService.delete(vetForDelete)
    }

    @PostMapping("/vet/create-vet")
    fun create(@RequestBody vetDTO: VetDTO) {
        this.vetService.create(vetDTO.fromJSON(vetDTO))
    }

    @PostMapping("/vet/filter/get-all-by-filter/{vetId}")
    fun getAllByFilter(@RequestBody vetFilterPetDTO: VetFilterPetDTO, @PathVariable vetId: Int): List<PetDTO> {
        val vetFilterPet: VetFilterPet = vetFilterPetDTO.fromJSON(vetFilterPetDTO)
        return vetService.getAllPetsFilter(vetFilterPet, vetId).map { it.toDTO() }
    }
}