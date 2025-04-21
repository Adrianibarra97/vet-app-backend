package ar.edu.unsam.proyecto.vetappbackend.domain
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "pet")
class Pet {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    var photo: String = ""
    var name: String = ""
    var age: Int = 0
    var breed: String = ""
    var sex: String = ""
    var weight: Double = 0.0
    var sterilized: Boolean = false
    var specie: String = ""
    var birth: LocalDate? = null

    @OneToOne
    @JoinColumn(name = "id_medical_history", referencedColumnName = "id")
    var medicalHistory: MedicalHistory? = null

    @ManyToOne
    @JoinColumn(name = "id_pet_owner", referencedColumnName = "id")
    var petOwner: PetOwner? = null
}

