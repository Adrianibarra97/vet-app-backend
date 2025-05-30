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

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "location_info_id", nullable = false, unique = true)
    lateinit var locationInfo: LocationInfo

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "auth_credentials_id", nullable = false, unique = true)
    lateinit var authCredentials: AuthCredentials

}