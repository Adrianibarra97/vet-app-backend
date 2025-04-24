package ar.edu.unsam.proyecto.vetappbackend.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import jakarta.persistence.*

@Entity
@Table(name = "user_data")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes(
    JsonSubTypes.Type(value = PetOwner::class, name =  "PETOWNER"),
    JsonSubTypes.Type(value = Vet::class, name =  "VET")
)
@Inheritance(strategy= InheritanceType.JOINED)
abstract class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
    var name: String = ""
    var surname: String = ""
    var username: String = ""
    var password: String = ""
}

@Entity
class PetOwner : UserData() {
    var dni: Int = 0
    var email: String = ""
    var telephone: String = ""
    var address: String = ""

    @OneToMany(mappedBy = "petOwner", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var pets: MutableSet<Pet> = mutableSetOf()
}

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
        joinColumns = [JoinColumn(name = "vet_id")],
        inverseJoinColumns = [JoinColumn(name = "pet_id")]
    )
    var patients: MutableSet<Pet> = mutableSetOf()
}
