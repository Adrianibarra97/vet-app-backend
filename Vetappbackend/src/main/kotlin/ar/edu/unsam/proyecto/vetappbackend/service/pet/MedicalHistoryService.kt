package ar.edu.unsam.proyecto.vetappbackend.service.pet

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import ar.edu.unsam.proyecto.vetappbackend.service.BaseService
import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.domain.shift.Recipe
import ar.edu.unsam.proyecto.vetappbackend.repository.pet.MedicalHistoryRepository
import ar.edu.unsam.proyecto.vetappbackend.repository.pet.PetRepository
import ar.edu.unsam.proyecto.vetappbackend.service.shift.MedicalShiftService

@Service
class MedicalHistoryService: BaseService<MedicalHistory> {

    @Autowired lateinit var medicalHistoryRepository: MedicalHistoryRepository

    @Autowired lateinit var medicalShiftService: MedicalShiftService

    override fun getOneById(idMedicalHistory: Int): MedicalHistory {
        return this.medicalHistoryRepository.findById(idMedicalHistory).orElseThrow {
            NotFoundException("No se encontró el historial médico indicado: $idMedicalHistory")
        }
    }

    override fun getAll(): List<MedicalHistory> {
        return this.medicalHistoryRepository.findAll().toList()
    }

    override fun create(newMedicalHistory: MedicalHistory) {
        this.medicalHistoryRepository.save(newMedicalHistory)
    }

    override fun delete(medicalHistoryDelete: MedicalHistory) {
        this.medicalHistoryRepository.delete(medicalHistoryDelete)
    }

    override fun update(medicalHistoryUpdate: MedicalHistory) {
        this.getOneById(medicalHistoryUpdate.id!!)
        this.medicalHistoryRepository.save(medicalHistoryUpdate)
    }

    fun getAllRecipes(idMedicalHistory: Int): List<Recipe>{
        val medicalHistory: MedicalHistory = this.getOneById(idMedicalHistory)
        return medicalShiftService.getAllRecipes(medicalHistory.pet?.id!!)
    }

    fun getAllVaccines(idMedicalHistory: Int): List<Vaccine> {
        val medicalHistory: MedicalHistory = this.getOneById(idMedicalHistory)
        return this.medicalHistoryRepository.getAllVaccines(medicalHistory.id!!)
    }

    fun getAllPreExistenceDisease(idMedicalHistory: Int): List<PreExistenceDisease> {
        val medicalHistory: MedicalHistory = this.getOneById(idMedicalHistory)
        return this.medicalHistoryRepository.getAllPreExistenceDisease(medicalHistory.id!!)
    }

    fun getAllStudyResult(idMedicalHistory: Int): List<StudyResult> {
        val medicalHistory: MedicalHistory = this.getOneById(idMedicalHistory)
        return this.medicalHistoryRepository.getAllStudyResult(medicalHistory.id!!)
    }

}