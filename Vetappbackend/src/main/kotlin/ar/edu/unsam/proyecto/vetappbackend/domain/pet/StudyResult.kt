package ar.edu.unsam.proyecto.vetappbackend.domain.pet
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "study_result")
class StudyResult {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @ManyToOne @JoinColumn(referencedColumnName = "id", name = "id_medical_history")
    var medicalHistory: MedicalHistory? = null

    var date: LocalDate? = null

    var fileUrl: String? = null

    var interpretation: String? = null

    @Enumerated(EnumType.STRING)
    var type: TypeOfStudyResult? = null

}

enum class TypeOfStudyResult { PHYSIOLOGICAL, PHARMACOLOGICAL, GENETIC, PATHOLOGICAL, CLINICAL }