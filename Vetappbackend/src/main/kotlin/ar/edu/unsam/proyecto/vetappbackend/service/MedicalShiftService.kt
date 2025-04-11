package ar.edu.unsam.proyecto.vetappbackend.service

import ar.edu.unsam.proyecto.vetappbackend.domain.MedicalShift
import ar.edu.unsam.proyecto.vetappbackend.domain.Pet
import ar.edu.unsam.proyecto.vetappbackend.repository.MedicalShiftRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate


@Service
class MedicalShiftService: BaseService<MedicalShift> {
    @Autowired
    private lateinit var medicalShiftRepository: MedicalShiftRepository

    fun getAllByShiftToday(date: LocalDate): List<Pet> {
        return medicalShiftRepository.getAllByShiftToday(date)
    }

    override fun getAll(): List<MedicalShift> {
        TODO("Not yet implemented")
    }

    override fun getOneById(objectId: Int): MedicalShift {
        TODO("Not yet implemented")
    }

    override fun delete(anObject: MedicalShift) {
        TODO("Not yet implemented")
    }

    override fun update(anObject: MedicalShift) {
        TODO("Not yet implemented")
    }

    override fun create(anObject: MedicalShift) {
        TODO("Not yet implemented")
    }
}
