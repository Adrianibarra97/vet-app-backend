package ar.edu.unsam.proyecto.vetappbackend.domain

import jakarta.persistence.*

@Entity
@Table(name = "Study_result")
class StudyResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    var name: String = ""
    var description: String = ""

    @ManyToOne
    @JoinColumn(name = "study_result_id") // Relación hacia MedicalHistory
    var medicalHistory: MedicalHistory? = null
}