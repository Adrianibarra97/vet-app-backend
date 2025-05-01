package ar.edu.unsam.proyecto.vetappbackend.service.pet

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import ar.edu.unsam.proyecto.vetappbackend.service.BaseService
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.Vaccine
import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.repository.pet.VaccineRepository

@Service
class VaccineService: BaseService<Vaccine> {

    @Autowired lateinit var vacinneRepository: VaccineRepository

    override fun getOneById(vacinneId: Int): Vaccine {
        return this.vacinneRepository.findById(vacinneId).orElseThrow {
            NotFoundException("No se encontró la vacuna indicado: $vacinneId")
        }
    }

    override fun getAll(): List<Vaccine> {
       return this.vacinneRepository.findAll().toList()
    }

    override fun create(newVaccine: Vaccine) {
        this.vacinneRepository.save(newVaccine)
    }

    override fun delete(vaccine: Vaccine) {
        return this.vacinneRepository.delete(vaccine)
    }

    override fun update(vaccineUpdate: Vaccine) {
        this.getOneById(vaccineUpdate.id!!)
        this.vacinneRepository.save(vaccineUpdate)
    }
}