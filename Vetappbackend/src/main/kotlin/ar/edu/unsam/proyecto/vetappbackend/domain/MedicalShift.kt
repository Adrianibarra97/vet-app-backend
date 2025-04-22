package ar.edu.unsam.proyecto.vetappbackend.domain
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "medical_shift")
class MedicalShift {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    var date: LocalDate? = null

    @ManyToOne
    @JoinColumn(name = "id_vet", referencedColumnName = "id")
    var vet: Vet? = null


    @ManyToOne
    @JoinColumn(name = "id_pet", referencedColumnName = "id")
    var patient: Pet? = null

}

