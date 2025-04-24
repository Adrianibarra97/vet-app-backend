package ar.edu.unsam.proyecto.vetappbackend.domain.pet
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "pet")
class Pet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @ManyToOne @JoinColumn(referencedColumnName = "id", name = "id_pet_owner")
    var petOwner: PetOwner? = null

    var age: Int = 0
    var name: String = ""
    var photo: String = ""
    var breed: String = ""
    var weight: Double = 0.0
    var birth: LocalDate? = null

    @Enumerated(EnumType.STRING)
    var sex: TypeOfSex? = null

    @Enumerated(EnumType.STRING)
    var specie: TypeOfSpecie? = null

}

enum class TypeOfSex { MACHO, HEMBRA, HERMAFRODITA }

enum class TypeOfSpecie{ AVE, PEZ, GATO, PERRO, GRANJA, ROEDOR, REPTIL, CABALLO}