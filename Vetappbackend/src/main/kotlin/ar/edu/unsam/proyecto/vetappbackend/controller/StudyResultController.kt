package ar.edu.unsam.proyecto.vetappbackend.controller

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.MedicalHistory
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.StudyResult
import ar.edu.unsam.proyecto.vetappbackend.dto.StudyResultDTO
import ar.edu.unsam.proyecto.vetappbackend.dto.fromJSON
import ar.edu.unsam.proyecto.vetappbackend.dto.toDTO
import ar.edu.unsam.proyecto.vetappbackend.service.MedicalHistoryService
import ar.edu.unsam.proyecto.vetappbackend.service.StudyResultService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
@RequestMapping("/study-result")
class StudyResultController {

    @Autowired
    private lateinit var studyResultService: StudyResultService

    @Autowired
    private lateinit var medicalHistoryService: MedicalHistoryService

    @GetMapping("/get-all")
    fun getAll(): List<StudyResultDTO> {
        return this.studyResultService.getAll().map { it.toDTO() }
    }

    @GetMapping("/get-one-by-id")
    fun getOneById(@RequestParam idStudyResult: Int): StudyResultDTO {
        return this.studyResultService.getOneById(idStudyResult).toDTO()
    }

    @PostMapping("/create")
    fun create(@RequestBody newStudyResultDTO: StudyResultDTO) {
        val medicalHistory: MedicalHistory = medicalHistoryService.getOneById(newStudyResultDTO.medicalHistoryId)
        this.studyResultService.create(newStudyResultDTO.fromJSON(newStudyResultDTO,medicalHistory))
    }

    @PutMapping("/update")
    fun update(@RequestBody newStudyResultDTO: StudyResultDTO) {
        val medicalHistory: MedicalHistory = medicalHistoryService.getOneById(newStudyResultDTO.medicalHistoryId)
        val studyResult: StudyResult = newStudyResultDTO.fromJSON(newStudyResultDTO, medicalHistory)
        studyResultService.update(studyResult)
    }

    @DeleteMapping("/delete")
    fun delete(@RequestParam idStudyResult: Int) {
        val studyResultForDelete: StudyResult = this.studyResultService.getOneById(idStudyResult)
        this.studyResultService.delete(studyResultForDelete)
    }

}