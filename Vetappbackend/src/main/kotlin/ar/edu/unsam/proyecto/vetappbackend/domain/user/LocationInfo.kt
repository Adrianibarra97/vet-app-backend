package ar.edu.unsam.proyecto.vetappbackend.domain.user
import jakarta.persistence.*

@Entity
@Table(name = "location_info")
data class LocationInfo (

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0,

    var address: String? = null,

    var country: String? = null,

    var province: String? = null,

    var locality: String? = null,

    var postalCode: String? = null

)