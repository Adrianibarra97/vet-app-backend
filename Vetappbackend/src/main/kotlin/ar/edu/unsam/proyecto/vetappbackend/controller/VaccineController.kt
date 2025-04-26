package ar.edu.unsam.proyecto.vetappbackend.controller

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.MedicalHistory
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.Vaccine
import ar.edu.unsam.proyecto.vetappbackend.dto.*
import ar.edu.unsam.proyecto.vetappbackend.service.MedicalHistoryService
import ar.edu.unsam.proyecto.vetappbackend.service.VaccineService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
@RequestMapping("/vaccines")
class VaccineController {

    @Autowired
    private lateinit var vaccineService: VaccineService

    @Autowired
    private lateinit var medicalHistoryService: MedicalHistoryService


    @GetMapping("/get-all")
    fun getAll(): List<VaccineDTO> {
        return this.vaccineService.getAll().map { it.toDTO() }
    }

    @GetMapping("/get-one-by-id")
    fun getOneById(@RequestParam vaccineId: Int): VaccineDTO {
        return this.vaccineService.getOneById(vaccineId).toDTO()
    }

    @PostMapping("/create")
    fun create(@RequestBody newVaccineDTO: VaccineDTO) {
        val medicalHistory: MedicalHistory = medicalHistoryService.getOneById(newVaccineDTO.medicalHistoryId)
        val vaccine: Vaccine = newVaccineDTO.fromJSON(newVaccineDTO, medicalHistory)
        this.vaccineService.create(vaccine)
    }

    @PutMapping("/update")
    fun update(@RequestBody newVaccineDTO: VaccineDTO) {
        val medicalHistory: MedicalHistory = medicalHistoryService.getOneById(newVaccineDTO.medicalHistoryId)
        val vaccine: Vaccine = newVaccineDTO.fromJSON(newVaccineDTO, medicalHistory)
        this.vaccineService.update(vaccine)
    }

    @DeleteMapping("/delete")
    fun delete(@RequestParam idVaccine: Int) {
        val vaccineForDelete: Vaccine = this.vaccineService.getOneById(idVaccine)
        this.vaccineService.delete(vaccineForDelete)
    }
}