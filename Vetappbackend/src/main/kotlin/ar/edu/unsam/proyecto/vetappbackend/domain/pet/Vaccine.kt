package ar.edu.unsam.proyecto.vetappbackend.domain.pet
import java.time.LocalDate
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import ar.edu.unsam.proyecto.vetappbackend.domain.type.*

@Entity
@Table(name = "vaccine")
class Vaccine {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    var batchNumber: Int = 0

    var description: String = ""

    var completed: Boolean = false

    var applicationDate: LocalDate? = null

    var expirationDate: LocalDate? = null

    @Column(nullable = false) @Enumerated(EnumType.STRING)
    var type: TypeOfVaccinePet? = null

    @ManyToOne @JoinColumn(referencedColumnName = "id", name = "id_medical_history")
    @OnDelete(action = OnDeleteAction.CASCADE)
    var medicalHistory: MedicalHistory? = null

}