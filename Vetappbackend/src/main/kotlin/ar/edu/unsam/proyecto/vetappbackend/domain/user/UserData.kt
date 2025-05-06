package ar.edu.unsam.proyecto.vetappbackend.domain.user

import jakarta.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
abstract class UserData {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null
    var dni: Int = 0
    var name: String = ""
    var surname: String = ""

    var photo: String = ""
    var email: String = ""
    var address: String = ""
    var telephone: String = ""

    var country: String = ""
    var province: String = ""
    var locality: String = ""
    var postalCode: String = ""

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "auth_credentials_id", nullable = false, unique = true)
    lateinit var authCredentials: AuthCredentials

}