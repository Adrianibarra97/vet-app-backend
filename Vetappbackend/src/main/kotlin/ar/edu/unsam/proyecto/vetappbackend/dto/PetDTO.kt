package ar.edu.unsam.proyecto.vetappbackend.dto

import ar.edu.unsam.proyecto.vetappbackend.domain.Pet
import java.time.LocalDate

data class PetDTO(
    val id: Int,
    val photo: String,
    val name: String,
    val age: Int,
    val breed: String,
    val sex: String,
    val weight: Double,
    val sterilized: Boolean,
    val specie: String,
    val birth: String?
)

fun Pet.toJSON(): PetDTO {
    return PetDTO(
        id = this.id,
        photo = this.photo,
        name = this.name,
        age = this.age,
        breed = this.breed,
        sex = this.sex,
        weight = this.weight,
        sterilized = this.sterilized,
        specie = this.specie,
        birth = this.birth?.toString()
    )
}
fun PetDTO.fromJSON(petDTO: PetDTO): Pet {
    val pet = Pet()
    pet.id = petDTO.id
    pet.photo = petDTO.photo
    pet.name = petDTO.name
    pet.age = petDTO.age
    pet.breed = petDTO.breed
    pet.sex = petDTO.sex
    pet.weight = petDTO.weight
    pet.sterilized = petDTO.sterilized
    pet.specie = petDTO.specie
    pet.birth = petDTO.birth?.let { LocalDate.parse(it) }

    return pet
}