package ar.edu.unsam.proyecto.vetappbackend.domain.user
import jakarta.persistence.*
import java.time.LocalDate

@Entity
class PetOwner: UserData() {

    var dni: Int = 0

    var age: Int = 0

    var petCount: Int = 0

    var birthdate: LocalDate? = null

}