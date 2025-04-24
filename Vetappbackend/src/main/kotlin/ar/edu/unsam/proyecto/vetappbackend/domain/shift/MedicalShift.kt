package ar.edu.unsam.proyecto.vetappbackend.domain.shift
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import jakarta.persistence.*
import java.time.*

@Entity
@Table(name = "medical_shift")
class MedicalShift {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @ManyToOne @JoinColumn(referencedColumnName = "id", name = "id_vet")
    var vet: Vet? = null

    @ManyToOne @JoinColumn(referencedColumnName = "id", name = "id_pet")
    var pet: Pet? = null

    var date: LocalDate? = null

    var hour: LocalTime? = null

}