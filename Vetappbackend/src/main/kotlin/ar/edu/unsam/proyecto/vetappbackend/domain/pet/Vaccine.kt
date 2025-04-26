package ar.edu.unsam.proyecto.vetappbackend.domain.pet
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "vaccine")
class Vaccine {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @ManyToOne @JoinColumn(referencedColumnName = "id", name = "id_medical_history")
    var medicalHistory: MedicalHistory? = null

    var applicationDate: LocalDate? = null
    var expirationDate: LocalDate? = null
    var description: String = ""
    var batchNumber: Int = 0
    var completed: Boolean = false

    @Enumerated(EnumType.STRING)
    var type: TypeOfVaccine? = null

}

enum class TypeOfVaccine { ANTIRRÁBICA, MOQUILLO, PARVOVIRUS, HEPATITIS, LEPTOSPIROSIS, PARAINFLUENZA, DESPARASITACIÓN }