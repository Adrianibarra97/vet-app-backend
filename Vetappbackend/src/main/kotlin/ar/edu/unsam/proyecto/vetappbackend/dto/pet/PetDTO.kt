package ar.edu.unsam.proyecto.vetappbackend.dto.pet

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.domain.type.*
import ar.edu.unsam.proyecto.vetappbackend.domain.user.PetOwner
import java.time.LocalDate
import kotlin.Int

data class PetDTO(
    val id: Int?,
    val age: Int,
    val name: String,
    val photo: String,
    val breed: String,
    val weight: Double,
    val birth: String,
    val sex: String,
    val specie: String,
    val sterilized: Boolean,
    //Medical History
    val idMedicalHistory: Int?,
    val summary: String,
    val createdAt: String,
    val updatedAt: String,
    //PetOwner
    val petOwnerId: Int
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
        sterilized = this.sterilized,
        idMedicalHistory = this.medicalHistory?.id!!,
        summary = this.medicalHistory?.summary!!,
        createdAt = this.medicalHistory?.createdAt!!.toString(),
        updatedAt = this.medicalHistory?.updatedAt!!.toString(),
        petOwnerId = this.petOwner?.id!!
    )
}

fun PetDTO.fromJSON(currentPetOwner: PetOwner): Pet {
    val petDTO = this
    return Pet().apply {
        id = petDTO.id?: 0
        age = petDTO.age
        name = petDTO.name
        photo = petDTO.photo
        breed = petDTO.breed
        weight = petDTO.weight
        birth = LocalDate.parse(petDTO.birth.toString())
        sterilized = petDTO.sterilized
        sex = TypeOfSexPet.valueOf(petDTO.sex.toString())
        specie = TypeOfSpeciePet.valueOf(petDTO.specie.toString())
        medicalHistory = MedicalHistory().apply {
            id = petDTO.idMedicalHistory ?: 0
            summary = petDTO.summary
            createdAt = petDTO.createdAt.let { LocalDate.parse(it) }
            updatedAt = petDTO.updatedAt.let { LocalDate.parse(it) }
        }
        petOwner = currentPetOwner
    }
}

class PetMedicalShiftDTO(
    val id: Int,
    val name: String
)

fun Pet.toPetMedicalShiftDTO(): PetMedicalShiftDTO {
    return PetMedicalShiftDTO(
        id = this.id!!,
        name = this.name
    )
}