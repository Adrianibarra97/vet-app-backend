package ar.edu.unsam.proyecto.vetappbackend.domain

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "Vaccines")
class Vaccine {

    @Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    var name: String = ""
    var description: String = ""
    var batchNumber: Int = 0
    var applicationDate: LocalDate = LocalDate.now()
    var expirationDate: LocalDate? = null
    var completed: Boolean = false

    @ManyToOne
    @JoinColumn(name = "vaccines_id") // Relación hacia MedicalHistory
    var medicalHistory: MedicalHistory? = null

}