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

    override fun getOneById(idVacinne: Int): Vaccine {
        return this.vacinneRepository.findById(idVacinne).orElseThrow {
            NotFoundException("No se encontró la vacuna indicado: $idVacinne")
        }
    }

    override fun getAll(): List<Vaccine> {
       return this.vacinneRepository.findAll().toList()
    }

    override fun create(newVaccine: Vaccine) {
        this.vacinneRepository.save(newVaccine)
    }

    override fun delete(vaccineDelete: Vaccine) {
        return this.vacinneRepository.delete(vaccineDelete)
    }

    override fun update(vaccineUpdate: Vaccine) {
        this.vacinneRepository.save(vaccineUpdate)
    }
}