package ar.edu.unsam.proyecto.vetappbackend.domain

import jakarta.persistence.*

@Entity
@Table(name = "Pre_existence_disease")
class PreExistenceDisease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    var name: String = ""
    var description: String = ""

    @ManyToOne
    @JoinColumn(name = "medical_history_id") // Relación hacia MedicalHistory
    var medicalHistory: MedicalHistory? = null
}