package ar.edu.unsam.proyecto.vetappbackend.service

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.PreExistenceDisease
import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.repository.PreExistenceDiseaseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PreExistenceDiseaseService: BaseService<PreExistenceDisease> {

    @Autowired
    lateinit var preExistenceDiseaseRepository: PreExistenceDiseaseRepository

    override fun getAll(): List<PreExistenceDisease> {
        return this.preExistenceDiseaseRepository.findAll().toList()
    }

    override fun getOneById(preExistenceDiseaseId: Int): PreExistenceDisease {
        return this.preExistenceDiseaseRepository.findById(preExistenceDiseaseId).orElseThrow {
            NotFoundException("No se encontró la enfermedad indicada: $preExistenceDiseaseId")
        }
    }

    override fun create(newPreExistenceDisease: PreExistenceDisease) {
        this.preExistenceDiseaseRepository.save(newPreExistenceDisease)
    }

    override fun delete(preExistenceDisease: PreExistenceDisease) {
        return this.preExistenceDiseaseRepository.delete(preExistenceDisease)
    }

    override fun update(preExistenceDiseaseUpdate: PreExistenceDisease) {
        val preExistenceDisease: PreExistenceDisease = this.getOneById(preExistenceDiseaseUpdate.id!!)
        preExistenceDisease.apply {
            this.id = preExistenceDiseaseUpdate.id
            this.medicalHistory = preExistenceDiseaseUpdate.medicalHistory
            this.isActive = preExistenceDiseaseUpdate.isActive
            this.observation = preExistenceDiseaseUpdate.observation
            this.diagnosisDate = preExistenceDiseaseUpdate.diagnosisDate
            this.severity = preExistenceDiseaseUpdate.severity
            this.type = preExistenceDiseaseUpdate.type


        }
        this.preExistenceDiseaseRepository.save(preExistenceDisease)
    }
}
