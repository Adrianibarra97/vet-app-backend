package ar.edu.unsam.proyecto.vetappbackend.dto

import ar.edu.unsam.proyecto.vetappbackend.domain.Pet
import java.time.LocalDate

data class PetDTO(
    val id: Int,
    val name: String,
    val age: Int,
    val birth: String?,
    val sex: String,
    val breed: String,
    val specie: String,
    val weight: Double,
    val sterilized: Boolean,
    val photo: String
)

fun Pet.toJSON(): PetDTO {
    return PetDTO(
        id = this.id,
        name = this.name,
        age = this.age,
        birth = this.birth?.toString(),
        sex = this.sex,
        breed = this.breed,
        specie = this.specie,
        weight = this.weight,
        sterilized = this.sterilized,
        photo = this.photo
    )
}
fun PetDTO.fromJSON(petDTO: PetDTO): Pet {
    val pet = Pet()
    pet.id = petDTO.id
    pet.name = petDTO.name
    pet.age = petDTO.age
    pet.birth = petDTO.birth?.let { LocalDate.parse(it) }
    pet.sex = petDTO.sex
    pet.breed = petDTO.breed
    pet.specie = petDTO.specie
    pet.weight = petDTO.weight
    pet.sterilized = petDTO.sterilized
    pet.photo = petDTO.photo
    return pet
}