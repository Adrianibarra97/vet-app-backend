package ar.edu.unsam.proyecto.vetappbackend.repository.pet

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.StudyResult
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StudyResultRepository: CrudRepository<StudyResult, Int>