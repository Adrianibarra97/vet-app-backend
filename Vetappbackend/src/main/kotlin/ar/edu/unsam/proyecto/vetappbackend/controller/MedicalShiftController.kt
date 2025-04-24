package ar.edu.unsam.proyecto.vetappbackend.controller
import ar.edu.unsam.proyecto.vetappbackend.dto.*
import ar.edu.unsam.proyecto.vetappbackend.domain.*
import ar.edu.unsam.proyecto.vetappbackend.service.*
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.Autowired

@RestController
@CrossOrigin("*")
class MedicalShiftController {
    @Autowired
    private lateinit var medicalShiftService: MedicalShiftService

    @Autowired
    private lateinit var vetService: VetService

    @Autowired
    private lateinit var petService: PetService


    @GetMapping("/medical-shift/get-all")
    fun getAll(): List<MedicalShiftResponseDTO> {
        return this.medicalShiftService.getAll().map { it.toJSON() }
    }

    @PostMapping("/medical-shift/create")
    fun create(@RequestBody newMedicalShiftRequestDTO: MedicalShiftRequestDTO) {
        val vet: Vet = vetService.getOneById(newMedicalShiftRequestDTO.vetId)
        val pet: Pet = petService.getOneById(newMedicalShiftRequestDTO.patientId)
        val medicalShift: MedicalShift = newMedicalShiftRequestDTO.fromJSON(vet, pet, newMedicalShiftRequestDTO)
        this.medicalShiftService.create(medicalShift)
    }

    @PutMapping("/medical-shift/update")
    fun update(@RequestBody newMedicalShiftRequestDTO: MedicalShiftRequestDTO) {
        val vet: Vet = vetService.getOneById(newMedicalShiftRequestDTO.vetId)
        val pet: Pet = petService.getOneById(newMedicalShiftRequestDTO.patientId)
        val medicalShift: MedicalShift = newMedicalShiftRequestDTO.fromJSON(vet, pet, newMedicalShiftRequestDTO)
        this.medicalShiftService.update(medicalShift)
    }

    @DeleteMapping("/medical-shift/delete")
    fun delete(@RequestParam idMedicalShift: Int) {
        val medicalShiftForDelete: MedicalShift = this.medicalShiftService.getOneById(idMedicalShift)
        this.medicalShiftService.delete(medicalShiftForDelete)
    }
}

