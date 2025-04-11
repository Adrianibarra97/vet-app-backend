package ar.edu.unsam.proyecto.vetappbackend.controller

import ar.edu.unsam.proyecto.vetappbackend.service.MedicalShiftService
import ar.edu.unsam.proyecto.vetappbackend.dto.PetDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@CrossOrigin("*")
class MedicalShiftController {

    @Autowired
    lateinit var medicalShiftService: MedicalShiftService

    @GetMapping("/shifts/today/{todayDate}")
    fun getPetsByShiftToday(@PathVariable todayDate: String): List<PetDTO> {
        val localDate = LocalDate.parse(todayDate) // Convertir String a LocalDate
        return medicalShiftService.getAllByShiftToday(localDate).map { PetDTO.fromPet(it) }
    }
}