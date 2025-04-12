package ar.edu.unsam.proyecto.vetappbackend.controller

import ar.edu.unsam.proyecto.vetappbackend.domain.Pet
import ar.edu.unsam.proyecto.vetappbackend.service.*
import org.springframework.beans.factory.annotation.*
import ar.edu.unsam.proyecto.vetappbackend.dto.*
import org.springframework.web.bind.annotation.*
import java.time.LocalDate


@RestController
@CrossOrigin("*")
class PetController {

    @Autowired
    private lateinit var petService: PetService

    @GetMapping("/pet/get-all")
    fun getAll(): List<PetDTO> {
        return this.petService.getAll().map { it.toJSON() }
    }

    @GetMapping("/pet/filter/get-all-by-name/{name}")
    fun getAllByName(@PathVariable name: String): List<PetDTO> {
        return this.petService.getAllByName(name).map { it.toJSON() }
    }

    @GetMapping("/shifts/today/{todayDate}")
    fun getPetsByShiftToday(@PathVariable todayDate: String): List<PetDTO> {
        val localDate = LocalDate.parse(todayDate) // Convertir String a LocalDate
        return this.petService.getAllByShiftToday(localDate).map { it.toJSON() }
    }

    @GetMapping("/pet/filter/get-all-pending-vaccine/{pendingVaccine}")
    fun getAllPendingVaccines(@PathVariable pendingVaccine: Boolean): List<PetDTO> {
        return this.petService.getAllPendingVaccines(pendingVaccine).map { it.toJSON() }
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
        val petForDelete: Pet = this.petService.getOneById(idPet.toInt())
        this.petService.delete(petForDelete)
    }

}