package ar.edu.unsam.proyecto.vetappbackend.domain.user
import jakarta.persistence.*

@Entity
@Table(name = "auth_credentials")
class AuthCredentials {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @Column(nullable = false) @Enumerated(EnumType.STRING)
    lateinit var typeOfUser: TypeOfUser

    @Column(unique = true, nullable = false)
    var username: String = ""

    @Column(nullable = false)
    var password: String = ""

}

enum class TypeOfUser { VET, PETOWNER }