package ar.edu.unsam.proyecto.vetappbackend.domain.user
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

    var landline: String = ""

    var username: String = ""

    var photo: String = ""


    @OneToOne(cascade = [(CascadeType.MERGE)]) @JoinColumn(name = "id_user_data", unique = true)
    lateinit var userData: UserData

}