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

    @Autowired private lateinit var medicalHistoryService: MedicalHistoryService

    @Autowired private lateinit var preExistenceDiseaseService: PreExistenceDiseaseService

    @GetMapping("/get-all")
    fun getAll(): List<PreExistenceDiseaseDTO> {
        return this.preExistenceDiseaseService.getAll().map { it.toDTO() }
    }

    @GetMapping("/get-one-by-id")
    fun getOneById(@RequestParam idPreExistenceDisease: Int): PreExistenceDiseaseDTO {
        return this.preExistenceDiseaseService.getOneById(idPreExistenceDisease).toDTO()
    }

    @PostMapping("/create")
    fun create(@RequestBody newPreExistenceDiseaseDTO: PreExistenceDiseaseDTO) {
        val medicalHistory: MedicalHistory = medicalHistoryService.getOneById(newPreExistenceDiseaseDTO.medicalHistoryId)
        val preExistenceDisease: PreExistenceDisease = newPreExistenceDiseaseDTO.fromJSON(medicalHistory)
        this.preExistenceDiseaseService.create(preExistenceDisease)
    }

    @PutMapping("/update")
    fun update(@RequestBody newPreExistenceDiseaseDTO: PreExistenceDiseaseDTO) {
        val medicalHistory: MedicalHistory = medicalHistoryService.getOneById(newPreExistenceDiseaseDTO.medicalHistoryId)
        val preExistenceDisease: PreExistenceDisease = newPreExistenceDiseaseDTO.fromJSON(medicalHistory)
        this.preExistenceDiseaseService.update(preExistenceDisease)
    }

    @DeleteMapping("/delete")
    fun delete(@RequestParam idPreExistenceDisease: Int) {
        val preExistenceDiseaseForDelete: PreExistenceDisease = this.preExistenceDiseaseService.getOneById(idPreExistenceDisease)
        this.preExistenceDiseaseService.delete(preExistenceDiseaseForDelete)
    }

}