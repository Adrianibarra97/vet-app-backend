package ar.edu.unsam.proyecto.vetappbackend.domain.user
import jakarta.persistence.*

@Entity
@Table(name = "auth_credentials")
data class AuthCredentials (

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,

    var password: String? = null,

    var username: String? = null,

    @Enumerated(EnumType.STRING)
    var typeOfUser: TypeOfUser? = null

)