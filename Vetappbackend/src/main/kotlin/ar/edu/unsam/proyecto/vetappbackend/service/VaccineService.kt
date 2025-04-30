package ar.edu.unsam.proyecto.vetappbackend.service

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.Vaccine
import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.repository.VaccineRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class VaccineService: BaseService <Vaccine> {

    @Autowired
    lateinit var vacinneRepository: VaccineRepository

    override fun getAll(): List<Vaccine> {
       return this.vacinneRepository.findAll().toList()
    }

    override fun getOneById(vacinneId: Int): Vaccine {
        return this.vacinneRepository.findById(vacinneId).orElseThrow {
            NotFoundException("No se encontró la vacuna indicado: $vacinneId")
        }
    }

    override fun create(newVaccine: Vaccine) {
        this.vacinneRepository.save(newVaccine)
    }

    override fun delete(vaccine: Vaccine) {
        return this.vacinneRepository.delete(vaccine)
    }

    override fun update(vaccineUpdate: Vaccine) {
        val vaccine: Vaccine = this.getOneById(vaccineUpdate.id!!)
        vaccine.apply {
            this.id = vaccineUpdate.id
            this.medicalHistory = vaccineUpdate.medicalHistory
            this.applicationDate = vaccineUpdate.applicationDate
            this.expirationDate = vaccineUpdate.expirationDate
            this.description = vaccineUpdate.description
            this.batchNumber = vaccineUpdate.batchNumber
            this.completed = vaccineUpdate.completed
            this.type = vaccineUpdate.type

        }
        this.vacinneRepository.save(vaccine)
    }
}