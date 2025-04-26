package ar.edu.unsam.proyecto.vetappbackend.repository

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.PreExistenceDisease
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PreExistenceDiseaseRepository: CrudRepository<PreExistenceDisease, Int> {
}