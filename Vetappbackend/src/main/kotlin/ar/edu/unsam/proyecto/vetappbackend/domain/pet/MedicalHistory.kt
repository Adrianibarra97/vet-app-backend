package ar.edu.unsam.proyecto.vetappbackend.domain.pet
import org.hibernate.annotations.OnDeleteAction
import org.hibernate.annotations.OnDelete
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "medical_history")
class MedicalHistory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    var summary: String = ""

    var createdAt: LocalDate? = null

    var updatedAt: LocalDate? = null

    //@OneToOne(mappedBy = "medical_history", fetch = FetchType.LAZY)
    //var pet: Pet? = null

}