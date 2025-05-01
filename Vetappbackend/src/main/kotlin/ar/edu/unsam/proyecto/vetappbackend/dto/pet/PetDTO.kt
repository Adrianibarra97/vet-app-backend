package ar.edu.unsam.proyecto.vetappbackend.dto.pet

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import java.time.LocalDate
import kotlin.Int

data class PetDTO(
    val id: Int,
    val age: Int,
    val name: String,
    val photo: String,
    val breed: String,
    val weight: Double,
    val birth: String,
    val sex: String,
    val specie: String,
)

fun Pet.toDTO(): PetDTO {
    return PetDTO(
        id = this.id!!,
        age = this.age,
        name = this.name,
        photo = this.photo,
        breed = this.breed,
        weight = this.weight,
        birth = this.birth.toString(),
        sex = this.sex.toString(),
        specie = this.specie.toString()
    )
}

fun PetDTO.fromJSON(petDTO: PetDTO): Pet {
    val pet = Pet()
    pet.id = petDTO.id
    pet.age = petDTO.age
    pet.name = petDTO.name
    pet.photo = petDTO.photo
    pet.breed = petDTO.breed
    pet.weight = petDTO.weight
    pet.birth = petDTO.birth.let { LocalDate.parse(it) }
    pet.sex = TypeOfSex.valueOf(this.sex)
    pet.specie = TypeOfSpecie.valueOf(this.specie)
    return pet
}