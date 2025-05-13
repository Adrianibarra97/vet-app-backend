package ar.edu.unsam.proyecto.vetappbackend.controller.pet
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.*
import ar.edu.unsam.proyecto.vetappbackend.dto.pet.*
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.service.pet.*

@RestController
@CrossOrigin("*")
@RequestMapping("/pre-existence-disease")
class PreExistenceDiseaseController {

    @Autowired private lateinit var petService: PetService

    @Autowired private lateinit var preExistenceDiseaseService: PreExistenceDiseaseService

    @GetMapping("/get-all")
    fun getAll(): List<PreExistenceDiseaseDTO> {
        return this.preExistenceDiseaseService.getAll().map { it.toDTO() }
    }

    @GetMapping("/get-one-by-id")
    fun getOneById(@RequestParam idPreExistenceDisease: Int): PreExistenceDiseaseDTO {
        return this.preExistenceDiseaseService.getOneById(idPreExistenceDisease).toDTO()
    }

    @DeleteMapping("/delete")
    fun delete(@RequestParam idPreExistenceDisease: Int) {
        val preExistenceDiseaseForDelete: PreExistenceDisease = this.preExistenceDiseaseService.getOneById(idPreExistenceDisease)
        this.preExistenceDiseaseService.delete(preExistenceDiseaseForDelete)
    }

    @PostMapping("/create")
    fun create(@RequestBody preExistenceDiseaseDTO: PreExistenceDiseaseDTO, @RequestParam idPet:Int) {
        val pet: Pet = this.petService.getOneById(idPet)
        val preExistenceDisease: PreExistenceDisease = preExistenceDiseaseDTO.fromJSON(pet)
        this.preExistenceDiseaseService.create(preExistenceDisease)
    }

    @PutMapping("/update")
    fun update(@RequestBody preExistenceDiseaseDTO: PreExistenceDiseaseDTO, @RequestParam idPet:Int) {
        val pet: Pet = this.petService.getOneById(idPet)
        val preExistenceDisease: PreExistenceDisease = preExistenceDiseaseDTO.fromJSON(pet)
        this.preExistenceDiseaseService.update(preExistenceDisease)
    }

}