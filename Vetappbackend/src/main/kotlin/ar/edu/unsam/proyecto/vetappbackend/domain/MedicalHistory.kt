package ar.edu.unsam.proyecto.vetappbackend.domain
import jakarta.persistence.*

@Entity
@Table(name = "medical_history")
class MedicalHistory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(name = "medical_history_preexistence_disease",
        joinColumns = [JoinColumn(name = "medical_history_id")],
        inverseJoinColumns = [JoinColumn(name = "preexistence_disease_id")]
    )
    var preExistingDiseases: MutableList<PreExistenceDisease> = mutableListOf()

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(name = "medical_history_study_result",
        joinColumns = [JoinColumn(name = "medical_history_id")],
        inverseJoinColumns = [JoinColumn(name = "study_result_id")]
    )
    var studyResults: MutableList<StudyResult> = mutableListOf()

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(name = "medical_history_vaccine",
        joinColumns = [JoinColumn(name = "medical_history_id")],
        inverseJoinColumns = [JoinColumn(name = "vaccine_id")]
    )
    var vaccines: MutableList<Vaccine> = mutableListOf()

    var notes: String = ""

}