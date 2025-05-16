package ar.edu.unsam.proyecto.vetappbackend.repository.pet

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface  MedicalHistoryRepository: CrudRepository<MedicalHistory, Int> {

    @Query("""
        SELECT vc FROM Vaccine vc
        WHERE vc.medicalHistory.id = :idMedicalHistory
        
    """)
    fun getAllVaccines(
        @Param("idMedicalHistory") idMedicalHistory: Int,
    ): List<Vaccine>



    @Query("""
        SELECT ps FROM PreExistenceDisease ps
        WHERE ps.medicalHistory.id = :idMedicalHistory
        
    """)
    fun getAllPreExistenceDisease(
        @Param("idMedicalHistory") idMedicalHistory: Int,
    ): List<PreExistenceDisease>



    @Query("""
        SELECT sr FROM StudyResult sr
        WHERE sr.medicalHistory.id = :idMedicalHistory
        
    """)
    fun getAllStudyResult(
        @Param("idMedicalHistory") idMedicalHistory: Int,
    ): List<StudyResult>


    @Query("""
        SELECT rp FROM Recipe rp
        WHERE rp.medicalHistory.id = :idMedicalHistory
        
    """)
    fun getAllRecipes(
        @Param("idMedicalHistory") idMedicalHistory: Int,
    ): List<Recipe>

}