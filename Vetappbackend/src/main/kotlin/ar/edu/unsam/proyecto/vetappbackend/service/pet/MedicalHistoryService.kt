package ar.edu.unsam.proyecto.vetappbackend.service.pet

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import ar.edu.unsam.proyecto.vetappbackend.service.BaseService
import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.MedicalHistory
import ar.edu.unsam.proyecto.vetappbackend.repository.pet.MedicalHistoryRepository

@Service
class MedicalHistoryService: BaseService<MedicalHistory> {

    @Autowired lateinit var medicalHistoryRepository: MedicalHistoryRepository

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

}