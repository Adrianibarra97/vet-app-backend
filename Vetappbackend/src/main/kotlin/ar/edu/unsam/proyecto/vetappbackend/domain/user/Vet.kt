package ar.edu.unsam.proyecto.vetappbackend.domain.user
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*

@Entity
class Vet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    var name: String = ""

    var surname: String = ""

    var licence: String = ""

    var speciality: String = ""

    var businessHours: String = ""

    var professionalEmail: String = ""

    var professionalAdress: String = ""

    var professionalTelephone: String = ""

    @OneToOne(cascade = [(CascadeType.MERGE)]) @JoinColumn(name = "id_user_data", unique = true)
    lateinit var userData: UserData

    @ManyToMany(mappedBy = "vets", cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @OnDelete(action = OnDeleteAction.CASCADE)
    var patients: MutableSet<Pet> = mutableSetOf()

}