package ar.edu.unsam.proyecto.vetappbackend.domain.user
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
class Vet : UserData() {
    var licence: String = ""
    var speciality: String = ""
    var businessHours: String = ""
    var professionalEmail: String = ""
    var professionalAdress: String = ""
    var professionalTelephone: String = ""

    @ManyToMany
    @JoinTable(
        name = "vet_pet",
        joinColumns = [JoinColumn(name = "id_vet")],
        inverseJoinColumns = [JoinColumn(name = "id_pet")]
    )
    var patients: MutableSet<Pet> = mutableSetOf()
}
