package ar.edu.unsam.proyecto.vetappbackend.controller.user
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.*
import ar.edu.unsam.proyecto.vetappbackend.dto.user.*
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
    fun getOneById(idPetOwner: Int): PetOwnerDTO {
        return this.petOwnerService.getOneById(idPetOwner).toDTO()
    }

    @DeleteMapping("/delete")
    fun delete(@RequestParam idPetOwner: Int) {
        val petOwnerForDelete: PetOwner = this.petOwnerService.getOneById(idPetOwner)
        this.petOwnerService.delete(petOwnerForDelete)
    }

    @PostMapping("/create")
    fun create(@RequestBody petOwnerDTO: PetOwnerDTO) {
        this.petOwnerService.create(petOwnerDTO.fromJSON())
    }

    @PutMapping("/update")
    fun update(@RequestBody petOwnerDTO: PetOwnerDTO) {
        this.petOwnerService.update(petOwnerDTO.fromJSON())
    }

}