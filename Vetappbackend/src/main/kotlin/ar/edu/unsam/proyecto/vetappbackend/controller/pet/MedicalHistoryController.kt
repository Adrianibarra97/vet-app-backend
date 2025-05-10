package ar.edu.unsam.proyecto.vetappbackend.controller.pet

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.MedicalHistory
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.Pet
import ar.edu.unsam.proyecto.vetappbackend.dto.pet.*
import ar.edu.unsam.proyecto.vetappbackend.dto.shift.RecipeResponseDTO
import ar.edu.unsam.proyecto.vetappbackend.dto.shift.toDTO
import ar.edu.unsam.proyecto.vetappbackend.dto.shift.toResponseDTO
import ar.edu.unsam.proyecto.vetappbackend.service.pet.MedicalHistoryService
import ar.edu.unsam.proyecto.vetappbackend.service.pet.PetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@CrossOrigin("*")
@RequestMapping("/medical-history")
class MedicalHistoryController {

    @Autowired
    private lateinit var medicalHistoryService: MedicalHistoryService

    @Autowired
    private lateinit var petService: PetService

    @GetMapping("/get-all")
    fun getAll(): List<MedicalHistoryDTO> {
        return this.medicalHistoryService.getAll().map { it.toDTO() }
    }

    @GetMapping("/get-one-by-id")
    fun getOneById(@RequestParam idMedicalHistory: Int): MedicalHistoryDTO {
        return this.medicalHistoryService.getOneById(idMedicalHistory).toDTO()
    }

    @PostMapping("/create")
    fun create(@RequestBody newMedicalHistoryDTO: MedicalHistoryDTO, @RequestParam idPet: Int) {
        val currentPet: Pet = petService.getOneById(idPet)
        this.medicalHistoryService.create(newMedicalHistoryDTO.fromJSON(currentPet))
    }

    @PutMapping("/update")
    fun update(@RequestBody newMedicalHistoryDTO: MedicalHistoryDTO, @RequestParam idPet: Int) {
        val currentPet: Pet = petService.getOneById(idPet)
        this.medicalHistoryService.update(newMedicalHistoryDTO.fromJSON(currentPet))
    }

    @DeleteMapping("delete")
    fun delete(@RequestParam idMedicalHistory: Int) {
        val medicalHistoryForDelete: MedicalHistory = this.medicalHistoryService.getOneById(idMedicalHistory)
        this.medicalHistoryService.delete(medicalHistoryForDelete)
    }

    @GetMapping("/get-all-pet-recipes")
    fun getAllRecipes(@RequestParam idMedicalHistory: Int): List<RecipeResponseDTO> {
        return this.medicalHistoryService.getAllRecipes(idMedicalHistory).map { it.toResponseDTO() }
    }

    @GetMapping("/get-all-pet-vaccines")
    fun getAllVaccines(@RequestParam idMedicalHistory: Int): List<VaccineDTO> {
        return this.medicalHistoryService.getAllVaccines(idMedicalHistory).map { it.toDTO() }
    }

    @GetMapping("/get-all-pet-pre-existence-disease")
    fun getAllPreExistenceDisease(@RequestParam idMedicalHistory: Int): List<PreExistenceDiseaseDTO> {
        return this.medicalHistoryService.getAllPreExistenceDisease(idMedicalHistory).map { it.toDTO() }
    }

    @GetMapping("/get-all-pet-study-result")
    fun getAllStudyResult(@RequestParam idMedicalHistory: Int): List<StudyResultDTO> {
        return this.medicalHistoryService.getAllStudyResult(idMedicalHistory).map { it.toDTO() }
    }


}