package ar.edu.unsam.proyecto.vetappbackend.service.user
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import ar.edu.unsam.proyecto.vetappbackend.service.BaseService
import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.domain.user.MedicalShift
import ar.edu.unsam.proyecto.vetappbackend.dto.filter.MedicalShiftFilter
import ar.edu.unsam.proyecto.vetappbackend.repository.user.MedicalShiftRepository

@Service
class MedicalShiftService: BaseService<MedicalShift> {

    @Autowired lateinit var medicalShiftRepository: MedicalShiftRepository

    override fun getOneById(idMedicalShift: Int): MedicalShift {
        return this.medicalShiftRepository.findById(idMedicalShift).orElseThrow {
            NotFoundException("No se encontró el turno indicado: $idMedicalShift")
        }
    }

    override fun getAll(): List<MedicalShift> {
        return medicalShiftRepository.findAll().toList()
    }

    override fun delete(medicalShiftDelete: MedicalShift) {
        this.medicalShiftRepository.delete(medicalShiftDelete)
    }

    override fun create(medicalShift: MedicalShift) {
        this.medicalShiftRepository.save(medicalShift)
    }

    override fun update(medicalShift: MedicalShift) {
        this.getOneById(medicalShift.id!!)
        this.medicalShiftRepository.save(medicalShift)
    }

    fun getMedicalShiftFilterVet(medicalShiftFilter: MedicalShiftFilter, idVet: Int): List<MedicalShift> {
        return medicalShiftRepository.getAllByFilterMedicalShiftVet(
            idVet,
            medicalShiftFilter.day,
            medicalShiftFilter.today,
            medicalShiftFilter.beginningOfWeek,
            medicalShiftFilter.endingOfWeek
        )
    }

    fun getMedicalShiftFilterPetOwner(medicalShiftFilter: MedicalShiftFilter, idPetOwer: Int): List<MedicalShift> {
        return medicalShiftRepository.getAllByFilterMedicalShiftPetOwner(
            idPetOwer,
            medicalShiftFilter.day,
            medicalShiftFilter.today,
            medicalShiftFilter.beginningOfWeek,
            medicalShiftFilter.endingOfWeek
        )
    }
}