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

    var fileUrl: String = ""

    var date: LocalDate? = null

    var description: String = ""

    @Column(nullable = false) @Enumerated(EnumType.STRING)
    var type: TypeOfStudyResult? = null

    @ManyToOne @JoinColumn(referencedColumnName = "id", name = "id_medical_history")
    @OnDelete(action = OnDeleteAction.CASCADE)
    var medicalHistory: MedicalHistory? = null

}

enum class TypeOfStudyResult { PHYSIOLOGICAL, PHARMACOLOGICAL, GENETIC, PATHOLOGICAL, CLINICAL, OTHER }