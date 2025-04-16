package ar.edu.unsam.proyecto.vetappbackend.repository

import ar.edu.unsam.proyecto.vetappbackend.domain.MedicalHistory
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface  MedicalHistoryRepository: CrudRepository<MedicalHistory, Int> {

}