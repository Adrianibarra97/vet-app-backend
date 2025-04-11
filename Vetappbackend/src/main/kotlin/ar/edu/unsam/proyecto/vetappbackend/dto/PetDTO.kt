package ar.edu.unsam.proyecto.vetappbackend.dto

import ar.edu.unsam.proyecto.vetappbackend.domain.Pet

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
) {
    companion object {
        fun toJSON(pet: Pet): PetDTO {
            return PetDTO(
                id = pet.id,
                name = pet.name,
                age = pet.age,
                birth = pet.birth?.toString(),
                sex = pet.sex,
                breed = pet.breed,
                specie = pet.specie,
                weight = pet.weight,
                sterilized = pet.sterilized,
                photo = pet.photo
            )
        }
    }
}