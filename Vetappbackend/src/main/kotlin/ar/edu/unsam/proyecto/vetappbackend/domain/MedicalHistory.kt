package ar.edu.unsam.proyecto.vetappbackend.domain

import jakarta.persistence.*

@Entity
@Table(name = "Medical_history")
class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
    var notes: String = ""

    @OneToMany(mappedBy = "medicalHistory", cascade = [CascadeType.ALL], orphanRemoval = true)
    var medicalShifts: MutableList<MedicalShift> = mutableListOf()

    @OneToMany(mappedBy = "medicalHistory", cascade = [CascadeType.ALL], orphanRemoval = true)
    var preExistingDisease: MutableList<PreExistenceDisease> = mutableListOf()

    @OneToMany(mappedBy = "medicalHistory", cascade = [CascadeType.ALL], orphanRemoval = true)
    var studiesReuslt: MutableList<StudyResult> = mutableListOf()

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "medical_history_vaccine",
        joinColumns = [JoinColumn(name = "medical_history_id")],
        inverseJoinColumns = [JoinColumn(name = "vaccine_id")]
    )
    var vaccines: MutableList<Vaccine> = mutableListOf()

}