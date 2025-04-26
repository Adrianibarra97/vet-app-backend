package ar.edu.unsam.proyecto.vetappbackend.service
import ar.edu.unsam.proyecto.vetappbackend.domain.shift.*
import ar.edu.unsam.proyecto.vetappbackend.dto.MedicalShiftFilter
import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.repository.MedicalShiftRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MedicalShiftService: BaseService<MedicalShift> {
    @Autowired lateinit var medicalShiftRepository: MedicalShiftRepository

    override fun getOneById(medicalShiftId: Int): MedicalShift {
        return this.medicalShiftRepository.findById(medicalShiftId).orElseThrow {
            NotFoundException("No se encontró el turno indicado: $medicalShiftId")
        }
    }

    override fun getAll(): List<MedicalShift> {
        return medicalShiftRepository.findAll().toList()
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
            this.pet = medicalShiftUpdate.pet
        }
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

    fun getMedicalShiftFilterPetOwner(medicalShiftFilter: MedicalShiftFilter, petOwerId: Int): List<MedicalShift> {
        return medicalShiftRepository.getAllByFilterMedicalShiftPetOwner(
            petOwerId,
            medicalShiftFilter.day,
            medicalShiftFilter.today,
            medicalShiftFilter.beginningOfWeek,
            medicalShiftFilter.endingOfWeek
        )
    }

}