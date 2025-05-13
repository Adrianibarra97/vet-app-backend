package ar.edu.unsam.proyecto.vetappbackend.domain.pet
import java.time.LocalDate
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import ar.edu.unsam.proyecto.vetappbackend.domain.type.TypeOfVaccine

@Entity
@Table(name = "vaccine")
class Vaccine {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    var batchNumber: Int? = null

    var description: String? = null

    var completed: Boolean = false

    var applicationDate: LocalDate? = null

    var expirationDate: LocalDate? = null

    @Column(nullable = false) @Enumerated(EnumType.STRING)
    var type: TypeOfVaccine? = null

    @ManyToOne @JoinColumn(referencedColumnName = "id", name = "id_medical_history")
    @OnDelete(action = OnDeleteAction.CASCADE)
    var pet: Pet? = null

}