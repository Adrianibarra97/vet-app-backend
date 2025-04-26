package ar.edu.unsam.proyecto.vetappbackend.domain.pet
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDate

@Entity
@Table(name = "pre_existence_disease")
class PreExistenceDisease {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @ManyToOne @JoinColumn(referencedColumnName = "id", name = "id_medical_history")
    @OnDelete(action = OnDeleteAction.CASCADE)
    var medicalHistory: MedicalHistory? = null

    var isActive: Boolean = true

    var observation: String? = null

    var diagnosisDate: LocalDate? = null

    @Enumerated(EnumType.STRING)
    var severity: TypeOfSeverity? = null

    @Enumerated(EnumType.STRING)
    var type: TypeOfPreExistinceDisease? = null
}

enum class TypeOfSeverity { Estable, Moderado, Grave, Crítico }

enum class TypeOfPreExistinceDisease { ASMA, DIABETES, MOQUILLO, PARVOVIRUS, EPILEPSIA, LEUCEMIA }