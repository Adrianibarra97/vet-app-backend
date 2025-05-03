package ar.edu.unsam.proyecto.vetappbackend.domain.user
import jakarta.persistence.*

@Entity
@Table(name = "user_data")
class UserData {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @Column(nullable = false)
    var password: String = ""

    @Column(unique = true, nullable = false)
    var username: String = ""

    @Column(nullable = false) @Enumerated(EnumType.STRING)
    lateinit var typeOfUser: TypeOfUser

}

enum class TypeOfUser { VET, PETOWNER }