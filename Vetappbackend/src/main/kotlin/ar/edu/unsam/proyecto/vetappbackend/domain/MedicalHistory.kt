package ar.edu.unsam.proyecto.vetappbackend.domain

import jakarta.persistence.*

@Entity
@Table(name = "Medical_history")
class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
    var notes: String = ""

    @OneToMany(mappedBy = "medicalHistory", cascade = [CascadeType.ALL], orphanRemoval = true)
    var preExistingDisease: MutableList<PreExistenceDisease> = mutableListOf()

    @OneToMany(mappedBy = "medicalHistory", cascade = [CascadeType.ALL])
    var studiesReuslt: MutableList<StudyResult> = mutableListOf()

    @OneToMany(mappedBy = "medicalHistory", cascade = [CascadeType.ALL])
    var vaccines: MutableList<Vaccine> = mutableListOf()

}