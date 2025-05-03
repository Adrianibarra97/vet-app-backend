package ar.edu.unsam.proyecto.vetappbackend.controller.pet
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.*
import ar.edu.unsam.proyecto.vetappbackend.dto.pet.*
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.service.pet.*

@RestController
@CrossOrigin("*")
@RequestMapping("/pet")
class PetController {

    @Autowired private lateinit var petService: PetService

    @GetMapping("/get-all")
    fun getAll(): List<PetDTO> {
        return this.petService.getAll().map { it.toDTO() }
    }

    @GetMapping("/get-one-by-id")
    fun getOneById(@RequestParam idPet: Int): PetDTO {
        return this.petService.getOneById(idPet).toDTO()
    }

    @PostMapping("/create")
    fun create(@RequestBody newPetDTO: PetDTO) {
      this.petService.create(newPetDTO.fromJSON())
    }

    @PutMapping("/update")
    fun update(@RequestBody newPetDTO: PetDTO) {
        this.petService.update(newPetDTO.fromJSON())
    }

    @DeleteMapping("delete")
    fun delete(@RequestParam idPet: Int) {
        val petForDelete: Pet = this.petService.getOneById(idPet)
        this.petService.delete(petForDelete)
    }

}