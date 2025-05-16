package ar.edu.unsam.proyecto.vetappbackend.domain.pet
import java.time.LocalDate
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import ar.edu.unsam.proyecto.vetappbackend.domain.user.Vet

@Entity
@Table(name = "recipe")
class Recipe {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    var date: LocalDate? = null

    var description: String = ""

    @ManyToOne @JoinColumn(referencedColumnName = "id", name = "id_vet")
    @OnDelete(action = OnDeleteAction.CASCADE)
    var vet: Vet? = null

    @ManyToOne @JoinColumn(referencedColumnName = "id", name = "id_medical_history")
    @OnDelete(action = OnDeleteAction.CASCADE)
    var medicalHistory: MedicalHistory? = null

}