package ar.edu.unsam.proyecto.vetappbackend.domain.shift
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(name = "recipe")
class Recipe {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "id_medical_shift")
    @OnDelete(action = OnDeleteAction.CASCADE)
    var medicalShift: MedicalShift? = null

    var descripcion: String = ""

}