package ar.edu.unsam.proyecto.vetappbackend.domain.pet
import java.time.LocalDate
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(name = "pre_existence_disease")
class PreExistenceDisease {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    var isActive: Boolean = true

    var observation: String? = null

    var diagnosisDate: LocalDate? = null

    @Column(nullable = false) @Enumerated(EnumType.STRING)
    var severity: TypeOfSeverity? = null

    @Column(nullable = false) @Enumerated(EnumType.STRING)
    var type: TypeOfPreExistinceDisease? = null

    @ManyToOne @JoinColumn(referencedColumnName = "id", name = "id_medical_history")
    @OnDelete(action = OnDeleteAction.CASCADE)
    var medicalHistory: MedicalHistory? = null

}

enum class TypeOfSeverity { Stable, Moderate, Critical }

enum class TypeOfPreExistinceDisease { ASTHMA, DIABETES, DISTETER, PARVOVIRUS, EPILEPSY, LEUKEMIA, OTHER }