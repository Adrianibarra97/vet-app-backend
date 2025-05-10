package ar.edu.unsam.proyecto.vetappbackend.domain.pet
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

    var age: Int = 0

    var name: String = ""

    var photo: String = ""

    var breed: String = ""

    var weight: Double = 0.0

    var birth: LocalDate? = null

    var sterilized: Boolean = false

    @Enumerated(EnumType.STRING)
    var sex: TypeOfSex? = null

    @Enumerated(EnumType.STRING)
    var specie: TypeOfSpecie? = null

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "id_pet_owner") @OnDelete(action = OnDeleteAction.CASCADE)
    var petOwner: PetOwner? = null

    @ManyToMany
    @JoinTable(name = "pet_vet", joinColumns = [JoinColumn(name = "id_pet")], inverseJoinColumns = [JoinColumn(name = "id_vet")])
    var vets: MutableSet<Vet> = mutableSetOf()

    @OneToOne(mappedBy = "pet", fetch = FetchType.LAZY)
    var medicalHistory: MedicalHistory? = null


}

enum class TypeOfSex { Macho, Hembra }

enum class TypeOfSpecie{ CAT, DOG, BIRD, FISH, FARM, RODENT, REPTILE, HORSE, OTHER }