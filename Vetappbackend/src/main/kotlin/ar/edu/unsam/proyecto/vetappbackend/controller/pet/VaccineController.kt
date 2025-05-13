package ar.edu.unsam.proyecto.vetappbackend.controller.pet
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.*
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.dto.pet.*
import ar.edu.unsam.proyecto.vetappbackend.service.pet.*

@RestController
@CrossOrigin("*")
@RequestMapping("/vaccines")
class VaccineController {

    @Autowired private lateinit var petService: PetService

    @Autowired private lateinit var vaccineService: VaccineService

    @GetMapping("/get-all")
    fun getAll(): List<VaccineDTO> {
        return this.vaccineService.getAll().map { it.toDTO() }
    }

    @GetMapping("/get-one-by-id")
    fun getOneById(@RequestParam idVaccine: Int): VaccineDTO {
        return this.vaccineService.getOneById(idVaccine).toDTO()
    }

    @PostMapping("/create")
    fun create(@RequestBody newVaccineDTO: VaccineDTO, @RequestParam idPet: Int) {
        val pet: Pet = this.petService.getOneById(idPet)
        val vaccine: Vaccine = newVaccineDTO.fromJSON(pet)
        this.vaccineService.create(vaccine)
    }

    @PutMapping("/update")
    fun update(@RequestBody newVaccineDTO: VaccineDTO, @RequestParam idPet: Int) {
        val pet: Pet = this.petService.getOneById(idPet)
        val vaccine: Vaccine = newVaccineDTO.fromJSON(pet)
        this.vaccineService.update(vaccine)
    }

    @DeleteMapping("/delete")
    fun delete(@RequestParam idVaccine: Int) {
        val vaccineForDelete: Vaccine = this.vaccineService.getOneById(idVaccine)
        this.vaccineService.delete(vaccineForDelete)
    }

}