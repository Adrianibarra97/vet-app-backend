package ar.edu.unsam.proyecto.vetappbackend.domain

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
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    var name: String = ""
    var surname: String = ""
    var dni: Int = 0
    var email: String = ""
    var telephone: String = ""
    var address: String = ""
    var username: String = ""
    var password: String = ""
}

@Entity
class PetOwner : UserData() {

}

@Entity
class Vet : UserData() {
    var licence: String = ""
    var jobTelephone: String = ""
    var speciality: String = ""
    var jobAdress: String = ""
    var professionalEmail: String = ""
    var businessHours: String = ""

    @OneToMany
    var medicalShift: MutableList<MedicalShift> = mutableListOf()

}