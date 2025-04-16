package ar.edu.unsam.proyecto.vetappbackend.domain
import jakarta.persistence.*

@Entity
@Table(name = "pre_existence_disease")
class PreExistenceDisease {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    var name: String = ""

    var description: String = ""
}