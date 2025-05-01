package ar.edu.unsam.proyecto.vetappbackend.domain.user
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import jakarta.persistence.*

@Entity
class PetOwner {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    var name: String = ""

    var surname: String = ""

    var dni: Int = 0

    var email: String = ""

    var address: String = ""

    var telephone: String = ""

    @OneToOne(cascade = [(CascadeType.MERGE)]) @JoinColumn(name = "id_user_data", unique = true)
    var userData: UserData? = null

}