package ar.edu.unsam.proyecto.vetappbackend.repository

import ar.edu.unsam.proyecto.vetappbackend.domain.Pet
import ar.edu.unsam.proyecto.vetappbackend.domain.Vet
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.time.LocalDate

interface VetRepository : CrudRepository<Vet, Int> {

    @Query("""
        SELECT p FROM Vet v
        JOIN v.patients p
        WHERE v.id = :idVet
    """)
    fun findAllPetsByVetId(@Param("idVet") idVet: Int): List<Pet>


    @Query("""
    SELECT DISTINCT ms.patient FROM MedicalShift ms
    JOIN ms.vet v
    WHERE v.id = :idVet
    AND (:name IS NULL OR ms.patient.name LIKE CONCAT('%', :name, '%'))
    AND (:shiftDate IS NULL OR ms.date = :shiftDate)
""")
    fun getAllByFilter(
        @Param("idVet") idVet: Int,
        @Param("name") name: String?,
        @Param("shiftDate") shiftDate: LocalDate?
    ): List<Pet>
}

