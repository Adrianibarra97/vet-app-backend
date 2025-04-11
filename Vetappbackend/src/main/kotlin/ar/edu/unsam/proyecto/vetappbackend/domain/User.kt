package ar.edu.unsam.proyecto.vetappbackend.domain

abstract class User {
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

class PetOwner : User() {

}

class Vet : User() {
    var licence: String? = ""
}