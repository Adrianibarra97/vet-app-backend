package ar.edu.unsam.proyecto.vetappbackend.domain.pet
import java.time.LocalDate
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(name = "study_result")
class StudyResult {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @ManyToOne @JoinColumn(referencedColumnName = "id", name = "id_medical_history")
    @OnDelete(action = OnDeleteAction.CASCADE)
    var medicalHistory: MedicalHistory? = null

    var date: LocalDate? = null

    var fileUrl: String? = null

    var interpretation: String? = null

    @Column(nullable = false) @Enumerated(EnumType.STRING)
    var type: TypeOfStudyResult? = null

}

enum class TypeOfStudyResult { PHYSIOLOGICAL, PHARMACOLOGICAL, GENETIC, PATHOLOGICAL, CLINICAL, OTHER }