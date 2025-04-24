package ar.edu.unsam.proyecto.vetappbackend.domain.shift
import jakarta.persistence.*

@Entity
@Table(name = "recipe")
class Recipe {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @ManyToOne @JoinColumn(referencedColumnName = "id", name = "id_medical_shift")
    var medicalShift: MedicalShift? = null

    var descripcion: String = ""

}