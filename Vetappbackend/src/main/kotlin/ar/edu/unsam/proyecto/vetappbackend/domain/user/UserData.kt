package ar.edu.unsam.proyecto.vetappbackend.domain.user
import jakarta.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
abstract class UserData {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    var dni: Int? = null

    var name: String? = null

    var surname: String? = null

    var photo: String? = null

    var email: String? = null

    var telephone: String? = null

}