package ar.edu.unsam.proyecto.vetappbackend.domain.shift
import java.time.*
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*

@Entity
@Table(name = "medical_shift")
class MedicalShift {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    var date: LocalDate? = null

    var hour: LocalTime? = null

    @ManyToOne @JoinColumn(referencedColumnName = "id", name = "id_pet")
    @OnDelete(action = OnDeleteAction.CASCADE)
    var pet: Pet? = null

    @ManyToOne @JoinColumn(referencedColumnName = "id", name = "id_vet")
    @OnDelete(action = OnDeleteAction.CASCADE)
    var vet: Vet? = null

}