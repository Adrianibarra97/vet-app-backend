package ar.edu.unsam.proyecto.vetappbackend.domain.pet
import ar.edu.unsam.proyecto.vetappbackend.domain.type.TypeOfSex
import ar.edu.unsam.proyecto.vetappbackend.domain.type.TypeOfSpecie
import java.time.LocalDate
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*

@Entity
@Table(name = "pet")
class Pet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    var age: Int? = null

    var name: String? = null

    var photo: String? = null

    var breed: String? = null

    var weight: Double? = null

    var birth: LocalDate? = null

    var sterilized: Boolean = false

    @Enumerated(EnumType.STRING)
    var sex: TypeOfSex? = null

    @Enumerated(EnumType.STRING)
    var specie: TypeOfSpecie? = null

    @ManyToOne @JoinColumn(referencedColumnName = "id", name = "id_pet_owner")
    @OnDelete(action = OnDeleteAction.CASCADE)
    var petOwner: PetOwner? = null

    @ManyToMany
    @JoinTable(
        name = "pet_vet",
        joinColumns = [JoinColumn(name = "id_pet")],
        inverseJoinColumns = [JoinColumn(name = "id_vet")]
    )
    var vets: MutableSet<Vet> = mutableSetOf()

}