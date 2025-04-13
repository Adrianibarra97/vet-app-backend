package ar.edu.unsam.proyecto.vetappbackend.domain

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import jakarta.persistence.*


@Entity
/*@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)*

 */
@JsonSubTypes(
    JsonSubTypes.Type(value = PetOwner::class, name =  "Pet_Owner"),
    JsonSubTypes.Type(value = Vet::class, name =  "Vet")
)
@Inheritance(strategy= InheritanceType.JOINED)
abstract class User {
    @Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

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
class PetOwner : User() {

}

@Entity
class Vet : User() {
    var licence: String = ""
    var jobTelephone: String = ""
    var speciality: String = ""
    var jobAdress: String = ""
    var professionalEmail: String = ""
    var businessHours: String = ""

    @OneToMany
    var medicalShift: MutableList<MedicalShift> = mutableListOf()

}