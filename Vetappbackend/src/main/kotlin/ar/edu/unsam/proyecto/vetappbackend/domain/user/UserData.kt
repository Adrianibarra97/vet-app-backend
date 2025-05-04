package ar.edu.unsam.proyecto.vetappbackend.domain.user

import jakarta.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
abstract class UserData {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null

    var name: String = ""

    var surname: String = ""

    var email: String = ""

    var address: String = ""

    var telephone: String = ""

    var photo: String = ""

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "auth_credentials_id", nullable = false, unique = true)
    lateinit var authCredentials: AuthCredentials

}