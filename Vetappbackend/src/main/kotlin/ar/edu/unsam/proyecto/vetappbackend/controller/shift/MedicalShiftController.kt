package ar.edu.unsam.proyecto.vetappbackend.controller.shift
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.*
import ar.edu.unsam.proyecto.vetappbackend.dto.shift.*
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import ar.edu.unsam.proyecto.vetappbackend.domain.shift.*
import ar.edu.unsam.proyecto.vetappbackend.service.pet.*
import ar.edu.unsam.proyecto.vetappbackend.service.user.*
import ar.edu.unsam.proyecto.vetappbackend.service.shift.*

@RestController
@CrossOrigin("*")
@RequestMapping("/medical-shift")
class MedicalShiftController {

    @Autowired private lateinit var vetService: VetService

    @Autowired private lateinit var petService: PetService

    @Autowired private lateinit var medicalShiftService: MedicalShiftService

    @GetMapping("/get-all")
    fun getAll(): List<MedicalShiftResponseDTO> {
        return this.medicalShiftService.getAll().map { it.toDTO() }
    }

    @GetMapping("/get-one-by-id")
    fun getOneById(@RequestParam idMedicalShift: Int): MedicalShiftResponseDTO {
        return this.medicalShiftService.getOneById(idMedicalShift).toDTO()
    }

    @PostMapping("/create")
    fun create(@RequestBody newMedicalShiftRequestDTO: MedicalShiftRequestDTO) {
        val vet: Vet = vetService.getOneById(newMedicalShiftRequestDTO.vetId)
        val pet: Pet = petService.getOneById(newMedicalShiftRequestDTO.petId)
        val medicalShift: MedicalShift = newMedicalShiftRequestDTO.fromJSON(vet, pet)
        this.medicalShiftService.create(medicalShift)
    }

    @PutMapping("/update")
    fun update(@RequestBody newMedicalShiftRequestDTO: MedicalShiftRequestDTO) {
        val vet: Vet = vetService.getOneById(newMedicalShiftRequestDTO.vetId)
        val pet: Pet = petService.getOneById(newMedicalShiftRequestDTO.petId)
        val medicalShift: MedicalShift = newMedicalShiftRequestDTO.fromJSON(vet, pet)
        this.medicalShiftService.update(medicalShift)
    }

    @DeleteMapping("/delete")
    fun delete(@RequestParam idMedicalShift: Int) {
        val medicalShiftForDelete: MedicalShift = this.medicalShiftService.getOneById(idMedicalShift)
        this.medicalShiftService.delete(medicalShiftForDelete)
    }

}