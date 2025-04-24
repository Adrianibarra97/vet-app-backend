package ar.edu.unsam.proyecto.vetappbackend.domain.user
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import jakarta.persistence.*

@Entity
class PetOwner : UserData() {
    var dni: Int = 0
    var email: String = ""
    var telephone: String = ""
    var address: String = ""

    @OneToMany(mappedBy = "petOwner", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var pets: MutableSet<Pet> = mutableSetOf()
}