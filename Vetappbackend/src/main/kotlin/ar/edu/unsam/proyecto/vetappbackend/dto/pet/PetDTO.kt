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
    val sterilized: Boolean
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
        specie = this.specie.toString(),
        sterilized = this.sterilized
    )
}

fun PetDTO.fromJSON(): Pet {
    val petDTO = this
    return Pet().apply {
        id = petDTO.id
        age = petDTO.age
        name = petDTO.name
        photo = petDTO.photo
        breed = petDTO.breed
        weight = petDTO.weight
        birth = LocalDate.parse(petDTO.birth.toString())
        sterilized = petDTO.sterilized
        sex = TypeOfSex.valueOf(petDTO.sex.toString())
        specie = TypeOfSpecie.valueOf(petDTO.specie.toString())
    }
}