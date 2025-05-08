package ar.edu.unsam.proyecto.vetappbackend.domain.user
import jakarta.persistence.*

@Entity
class PetOwner: UserData() {

    var emergencyContactName: String? = null

    var emergencyContactPhone: String? = null

}