package ar.edu.unsam.proyecto.vetappbackend.controller

import ar.edu.unsam.proyecto.vetappbackend.dto.PetDTO
import ar.edu.unsam.proyecto.vetappbackend.service.PetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
@CrossOrigin("*")
class PetController {

    @Autowired
    private lateinit var petService: PetService

    @GetMapping("/pet/get-all")
    fun getAll(): List<PetDTO> =
        this.petService.getAll().map { PetDTO.fromPet(it) }


    @GetMapping("/pet/filter/get-all-by-name/{name}")
    fun getAllByName(@PathVariable name: String): List<PetDTO> =
        this.petService.getAllByName(name).map { PetDTO.fromPet(it) }
}