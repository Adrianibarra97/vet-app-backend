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

    override fun getOneById(medicalHistoryId: Int): MedicalHistory {
        return this.medicalHistoryRepository.findById(medicalHistoryId).orElseThrow {
            NotFoundException("No se encontró el historial médico indicado: $medicalHistoryId")
        }
    }

    override fun getAll(): List<MedicalHistory> {
        return this.medicalHistoryRepository.findAll().toList()
    }

    override fun create(medicalHistory: MedicalHistory) {
        this.medicalHistoryRepository.save(medicalHistory)
    }

    override fun delete(medicalHistory: MedicalHistory) {
        this.medicalHistoryRepository.delete(medicalHistory)
    }

    override fun update(medicalHistoryUpdate: MedicalHistory) {
        this.getOneById(medicalHistoryUpdate.id!!)
        this.medicalHistoryRepository.save(medicalHistoryUpdate)
    }

}