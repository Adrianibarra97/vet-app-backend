package ar.edu.unsam.proyecto.vetappbackend.domain.user
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*

@Entity
class Vet: UserData() {

    var licence: String = ""

    var speciality: String = ""

    var businessHours: String = ""

    var professionalEmail: String = ""

    var professionalAdress: String = ""

    var professionalTelephone: String = ""

    @ManyToMany(mappedBy = "vets", cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @OnDelete(action = OnDeleteAction.CASCADE)
    var patients: MutableSet<Pet> = mutableSetOf()

}