package ar.edu.unsam.proyecto.vetappbackend.domain.pet
import java.time.LocalDate
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import ar.edu.unsam.proyecto.vetappbackend.domain.type.*

@Entity
@Table(name = "pre_existence_disease")
class PreExistenceDisease {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    var isActive: Boolean = true

    var observation: String = ""

    var diagnosisDate: LocalDate = LocalDate.now()

    @Column(nullable = false) @Enumerated(EnumType.STRING)
    var severity: TypeOfSeverityPet? = null

    @Column(nullable = false) @Enumerated(EnumType.STRING)
    var type: TypeOfPreExistenceDiseasePet? = null

    @ManyToOne @JoinColumn(referencedColumnName = "id", name = "id_medical_history")
    @OnDelete(action = OnDeleteAction.CASCADE)
    var medicalHistory: MedicalHistory? = null

}

