package ar.edu.unsam.proyecto.vetappbackend.service
import ar.edu.unsam.proyecto.vetappbackend.domain.MedicalShift
import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.repository.MedicalShiftRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MedicalShiftService: BaseService<MedicalShift> {
    @Autowired
    lateinit var medicalShiftRepository: MedicalShiftRepository

    override fun getAll(): List<MedicalShift> {
        return medicalShiftRepository.findAll().toList()
    }

    override fun getOneById(medicalShiftId: Int): MedicalShift {
        return this.medicalShiftRepository.findById(medicalShiftId).orElseThrow {
            NotFoundException("No se encontró el turno indicado: $medicalShiftId")
        }
    }

    override fun create(newMedicalShift: MedicalShift) {
        this.medicalShiftRepository.save(newMedicalShift)
    }

    override fun delete(medicalShiftDelete: MedicalShift) {
        this.medicalShiftRepository.delete(medicalShiftDelete)
    }

    override fun update(medicalShiftUpdate: MedicalShift) {
        val medicalShift: MedicalShift = this.getOneById(medicalShiftUpdate.id!!)
        medicalShift.apply {
            this.id = medicalShiftUpdate.id
            this.date = medicalShiftUpdate.date
            this.hour = medicalShiftUpdate.hour
            this.vet = medicalShiftUpdate.vet
            this.patient = medicalShiftUpdate.patient
        }
        this.medicalShiftRepository.save(medicalShift)
    }

}