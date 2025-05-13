package ar.edu.unsam.proyecto.vetappbackend.controller.pet
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.*
import ar.edu.unsam.proyecto.vetappbackend.dto.pet.*
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.service.pet.*

@RestController
@CrossOrigin("*")
@RequestMapping("/study-result")
class StudyResultController {

    @Autowired private lateinit var petService: PetService

    @Autowired private lateinit var studyResultService: StudyResultService

    @GetMapping("/get-all")
    fun getAll(): List<StudyResultDTO> {
        return this.studyResultService.getAll().map { it.toDTO() }
    }

    @GetMapping("/get-one-by-id")
    fun getOneById(@RequestParam idStudyResult: Int): StudyResultDTO {
        return this.studyResultService.getOneById(idStudyResult).toDTO()
    }

    @DeleteMapping("/delete")
    fun delete(@RequestParam idStudyResult: Int) {
        val studyResultForDelete: StudyResult = this.studyResultService.getOneById(idStudyResult)
        this.studyResultService.delete(studyResultForDelete)
    }

    @PostMapping("/create")
    fun create(@RequestBody studyResultDTO: StudyResultDTO, @RequestParam idPet: Int) {
        val pet: Pet = this.petService.getOneById(idPet)
        val studyResult: StudyResult = studyResultDTO.fromJSON(pet)
        this.studyResultService.create(studyResult)
    }

    @PutMapping("/update")
    fun update(@RequestBody studyResultDTO: StudyResultDTO, @RequestParam idPet: Int) {
        val pet: Pet = this.petService.getOneById(idPet)
        val studyResult: StudyResult = studyResultDTO.fromJSON(pet)
        this.studyResultService.update(studyResult)
    }


}