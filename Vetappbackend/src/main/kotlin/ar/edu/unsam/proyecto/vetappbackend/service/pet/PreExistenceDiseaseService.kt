package ar.edu.unsam.proyecto.vetappbackend.service.pet
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import ar.edu.unsam.proyecto.vetappbackend.service.BaseService
import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.PreExistenceDisease
import ar.edu.unsam.proyecto.vetappbackend.repository.pet.PreExistenceDiseaseRepository

@Service
class PreExistenceDiseaseService: BaseService<PreExistenceDisease> {

    @Autowired lateinit var preExistenceDiseaseRepository: PreExistenceDiseaseRepository

    override fun getOneById(idPreExistenceDisease: Int): PreExistenceDisease {
        return this.preExistenceDiseaseRepository.findById(idPreExistenceDisease).orElseThrow {
            NotFoundException("No se encontró la enfermedad indicada: $idPreExistenceDisease")
        }
    }

    override fun getAll(): List<PreExistenceDisease> {
        return this.preExistenceDiseaseRepository.findAll().toList()
    }

    @Transactional
    override fun create(newPreExistenceDisease: PreExistenceDisease) {
        this.preExistenceDiseaseRepository.save(newPreExistenceDisease)
    }

    @Transactional
    override fun delete(preExistenceDiseaseDelete: PreExistenceDisease) {
        return this.preExistenceDiseaseRepository.delete(preExistenceDiseaseDelete)
    }

    @Transactional
    override fun update(preExistenceDiseaseUpdate: PreExistenceDisease) {
        this.preExistenceDiseaseRepository.save(preExistenceDiseaseUpdate)
    }

}