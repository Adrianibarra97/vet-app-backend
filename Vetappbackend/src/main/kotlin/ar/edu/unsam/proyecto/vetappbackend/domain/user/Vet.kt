package ar.edu.unsam.proyecto.vetappbackend.domain.user
import jakarta.persistence.*

@Entity
class Vet: UserData() {

    var licence: String? = null

    var speciality: String? = null

    var businessHours: String? = null

    var professionalEmail: String? = null

    var professionalTelephone: String? = null

    var professionalAddress: String? = null

    var professionalLocality: String? = null

    var professionalPostalCode: String? = null

}