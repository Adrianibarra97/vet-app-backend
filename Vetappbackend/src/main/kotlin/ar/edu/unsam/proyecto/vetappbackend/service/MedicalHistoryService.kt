package ar.edu.unsam.proyecto.vetappbackend.service

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.MedicalHistory
import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.repository.MedicalHistoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MedicalHistoryService: BaseService<MedicalHistory> {

    @Autowired
    lateinit var medicalHistoryRepository: MedicalHistoryRepository

    override fun getAll(): List<MedicalHistory> {
        TODO("Not yet implemented")
    }

    override fun getOneById(medicalHistoryId: Int): MedicalHistory {
        return this.medicalHistoryRepository.findById(medicalHistoryId).orElseThrow {
            NotFoundException("No se encontró el historial médico indicado: $medicalHistoryId")
        }
    }

    override fun delete(anObject: MedicalHistory) {
        TODO("Not yet implemented")
    }

    override fun update(anObject: MedicalHistory) {
        TODO("Not yet implemented")
    }

    override fun create(anObject: MedicalHistory) {
        TODO("Not yet implemented")
    }

}