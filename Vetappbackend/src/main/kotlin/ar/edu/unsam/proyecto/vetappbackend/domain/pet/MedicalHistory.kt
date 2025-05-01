package ar.edu.unsam.proyecto.vetappbackend.domain.pet
import org.hibernate.annotations.OnDeleteAction
import org.hibernate.annotations.OnDelete
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "medical_history")
class MedicalHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    var summary: String? = null

    var created_at: LocalDate? = null

    var updated_at: LocalDate? = null

    @OneToOne @JoinColumn(referencedColumnName = "id", name = "id_pet")
    @OnDelete(action = OnDeleteAction.CASCADE)
    var pet: Pet? = null

}