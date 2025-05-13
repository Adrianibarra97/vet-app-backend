package ar.edu.unsam.proyecto.vetappbackend.controller.user

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.Pet
import ar.edu.unsam.proyecto.vetappbackend.domain.user.MedicalShift
import ar.edu.unsam.proyecto.vetappbackend.domain.user.Vet
import ar.edu.unsam.proyecto.vetappbackend.dto.filter.MedicalShiftFilter
import ar.edu.unsam.proyecto.vetappbackend.dto.filter.MedicalShiftFilterDTO
import ar.edu.unsam.proyecto.vetappbackend.dto.filter.fromJSON
import ar.edu.unsam.proyecto.vetappbackend.dto.user.MedicalShiftDTO
import ar.edu.unsam.proyecto.vetappbackend.dto.user.MedicalShiftResponseDTO
import ar.edu.unsam.proyecto.vetappbackend.dto.user.fromJSON
import ar.edu.unsam.proyecto.vetappbackend.dto.user.toDTO
import ar.edu.unsam.proyecto.vetappbackend.service.pet.PetService
import ar.edu.unsam.proyecto.vetappbackend.service.user.MedicalShiftService
import ar.edu.unsam.proyecto.vetappbackend.service.user.PetOwnerService
import ar.edu.unsam.proyecto.vetappbackend.service.user.VetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin("*")
@RequestMapping("/medical-shift")
class MedicalShiftController {

    @Autowired
    private lateinit var petService: PetService

    @Autowired
    private lateinit var vetService: VetService

    @Autowired
    private lateinit var petOwnerService: PetOwnerService

    @Autowired
    private lateinit var medicalShiftService: MedicalShiftService

    @GetMapping("/get-all")
    fun getAll(): List<MedicalShiftResponseDTO> {
        return this.medicalShiftService.getAll().map { it.toDTO() }
    }

    @GetMapping("/get-one-by-id")
    fun getOneById(@RequestParam idMedicalShift: Int): MedicalShiftResponseDTO {
        return this.medicalShiftService.getOneById(idMedicalShift).toDTO()
    }

    @DeleteMapping("/delete")
    fun delete(@RequestParam idMedicalShift: Int) {
        val medicalShiftForDelete: MedicalShift = this.medicalShiftService.getOneById(idMedicalShift)
        this.medicalShiftService.delete(medicalShiftForDelete)
    }

    @PostMapping("/create")
    fun create(@RequestBody medicalShiftDTO: MedicalShiftDTO, @RequestParam idPet: Int, @RequestParam idVet: Int) {
        val pet: Pet = petService.getOneById(idPet)
        val vet: Vet = vetService.getOneById(idVet)
        val medicalShift: MedicalShift = medicalShiftDTO.fromJSON(pet, vet)
        this.medicalShiftService.create(medicalShift)
    }

    @PutMapping("/update")
    fun update(@RequestBody medicalShiftDTO: MedicalShiftDTO, @RequestParam idPet: Int, @RequestParam idVet: Int) {
        val pet: Pet = petService.getOneById(idPet)
        val vet: Vet = vetService.getOneById(idVet)
        val medicalShift: MedicalShift = medicalShiftDTO.fromJSON(pet, vet)
        this.medicalShiftService.update(medicalShift)
    }


    @PostMapping("/get-all-medical-shift-by-filter/pet-owner")
    fun getAllByFilterMedicalShiftPetOwner(@RequestBody medicalShiftFilterDTO: MedicalShiftFilterDTO, @RequestParam idPetOwner: Int): List<MedicalShiftResponseDTO> {
        this.petOwnerService.getOneById(idPetOwner)
        val medicalShiftFilter: MedicalShiftFilter = medicalShiftFilterDTO.fromJSON()
        return this.medicalShiftService.getMedicalShiftFilterPetOwner(medicalShiftFilter, idPetOwner).map { it.toDTO() }
    }

    @PostMapping("/get-all-medical-shift-by-filter/vet")
    fun getAllByFilterMedicalShiftVet(@RequestBody medicalShiftFilterDTO: MedicalShiftFilterDTO, @RequestParam idVet: Int): List<MedicalShiftResponseDTO> {
        this.vetService.getOneById(idVet)
        val medicalShiftFilter: MedicalShiftFilter = medicalShiftFilterDTO.fromJSON()
        return this.medicalShiftService.getMedicalShiftFilterVet(medicalShiftFilter, idVet).map { it.toDTO() }
    }

}