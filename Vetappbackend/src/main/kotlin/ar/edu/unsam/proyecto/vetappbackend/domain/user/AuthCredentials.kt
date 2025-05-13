package ar.edu.unsam.proyecto.vetappbackend.domain.user
import jakarta.persistence.*
import ar.edu.unsam.proyecto.vetappbackend.domain.type.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(name = "auth_credentials")
data class AuthCredentials (

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,

    var password: String? = null,

    var username: String? = null,

    @Enumerated(EnumType.STRING)
    var typeOfUser: TypeOfUser? = null,

    @OneToOne(cascade = [CascadeType.MERGE]) @JoinColumn(referencedColumnName = "id", name = "id_user_data")
    @OnDelete(action = OnDeleteAction.CASCADE)
    var userData: UserData? = null

)