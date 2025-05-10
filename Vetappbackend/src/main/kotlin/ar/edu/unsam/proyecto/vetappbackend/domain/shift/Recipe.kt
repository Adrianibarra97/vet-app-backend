package ar.edu.unsam.proyecto.vetappbackend.domain.shift
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.MedicalHistory
import ar.edu.unsam.proyecto.vetappbackend.domain.user.Vet
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDate

@Entity
@Table(name = "recipe")
class Recipe {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    var description: String = ""

    @ManyToOne @JoinColumn(referencedColumnName = "id", name = "id_medical_history")
    @OnDelete(action = OnDeleteAction.CASCADE)
    var medicalHistory: MedicalHistory? = null

    var date: LocalDate? = null

    @ManyToOne @JoinColumn(referencedColumnName = "id", name = "id_vet")
    @OnDelete(action = OnDeleteAction.CASCADE)
    var vet: Vet? = null

}