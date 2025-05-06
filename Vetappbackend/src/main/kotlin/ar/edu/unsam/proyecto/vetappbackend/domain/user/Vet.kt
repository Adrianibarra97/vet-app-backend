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

    var professionalTelephone: String = ""

    var professionalAddress: String = ""

    var professionalLocality: String = ""

    var professionalPostalCode: String = ""

    @ManyToMany(mappedBy = "vets", cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @OnDelete(action = OnDeleteAction.CASCADE)
    var patients: MutableSet<Pet> = mutableSetOf()

}