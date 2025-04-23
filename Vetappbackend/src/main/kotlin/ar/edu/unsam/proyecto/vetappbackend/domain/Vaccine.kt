package ar.edu.unsam.proyecto.vetappbackend.domain
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "vaccine")
class Vaccine {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    var name: String = ""
    var description: String = ""
    var batchNumber: Int = 0
    var applicationDate: LocalDate = LocalDate.now()
    var expirationDate: LocalDate? = null

    /*Recordar que se puede refactorizar y en vez de tener un booleano,
    que se calcule por fecha de aplicación y fecha de vencimiento en un método en el medical history*/
    var completed: Boolean = false

}