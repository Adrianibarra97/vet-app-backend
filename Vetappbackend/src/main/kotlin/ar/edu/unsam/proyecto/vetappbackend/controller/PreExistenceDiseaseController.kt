package ar.edu.unsam.proyecto.vetappbackend.controller

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.MedicalHistory
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.PreExistenceDisease
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.Vaccine
import ar.edu.unsam.proyecto.vetappbackend.dto.PreExistenceDiseaseDTO
import ar.edu.unsam.proyecto.vetappbackend.dto.VaccineDTO
import ar.edu.unsam.proyecto.vetappbackend.dto.fromJSON
import ar.edu.unsam.proyecto.vetappbackend.dto.toDTO
import ar.edu.unsam.proyecto.vetappbackend.service.MedicalHistoryService
import ar.edu.unsam.proyecto.vetappbackend.service.PreExistenceDiseaseService
import ar.edu.unsam.proyecto.vetappbackend.service.VaccineService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
@RequestMapping("/pre-existence-disease")
class PreExistenceDiseaseController {

    @Autowired
    private lateinit var preExistenceDiseaseService: PreExistenceDiseaseService

    @Autowired
    private lateinit var medicalHistoryService: MedicalHistoryService

    @GetMapping("/get-all")
    fun getAll(): List<PreExistenceDiseaseDTO> {
        return this.preExistenceDiseaseService.getAll().map { it.toDTO() }
    }

    @GetMapping("/get-one-by-id")
    fun getOneById(@RequestParam preexistenceDiseaseId: Int): PreExistenceDiseaseDTO {
        return this.preExistenceDiseaseService.getOneById(preexistenceDiseaseId).toDTO()
    }

    @PostMapping("/create")
    fun create(@RequestBody newPreExistenceDiseaseDTO: PreExistenceDiseaseDTO) {
        val medicalHistory: MedicalHistory = medicalHistoryService.getOneById(newPreExistenceDiseaseDTO.medicalHistoryId)
        val preExistenceDisease: PreExistenceDisease = newPreExistenceDiseaseDTO.fromJSON(newPreExistenceDiseaseDTO, medicalHistory)
        this.preExistenceDiseaseService.create(preExistenceDisease)
    }

    @PutMapping("/update")
    fun update(@RequestBody newPreExistenceDiseaseDTO: PreExistenceDiseaseDTO) {
        val medicalHistory: MedicalHistory = medicalHistoryService.getOneById(newPreExistenceDiseaseDTO.medicalHistoryId)
        val preExistenceDisease: PreExistenceDisease = newPreExistenceDiseaseDTO.fromJSON(newPreExistenceDiseaseDTO, medicalHistory)
        this.preExistenceDiseaseService.update(preExistenceDisease)
    }

    @DeleteMapping("/delete")
    fun delete(@RequestParam preExistenceDiseaseId: Int) {
        val preExistenceDiseaseForDelete: PreExistenceDisease = this.preExistenceDiseaseService.getOneById(preExistenceDiseaseId)
        this.preExistenceDiseaseService.delete(preExistenceDiseaseForDelete)
    }

}