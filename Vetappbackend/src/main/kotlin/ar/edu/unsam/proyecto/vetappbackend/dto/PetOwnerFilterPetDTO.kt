package ar.edu.unsam.proyecto.vetappbackend.dto


class PetOwnerFilterPet {
    var name: String? = null
    var hasMedicalShift: Boolean? = null
    var hasPendingVaccine: Boolean? = null
}

class PetOwnerFilterPetDTO (
    val name: String?,
    val hasMedicalShift: Boolean?,
    val hasPendingVaccine: Boolean?
)

fun PetOwnerFilterPet.toJSON(): PetOwnerFilterPetDTO {
    return PetOwnerFilterPetDTO(
        name = this.name.toString(),
        hasMedicalShift = this.hasMedicalShift,
        hasPendingVaccine = this.hasPendingVaccine
    )
}

fun PetOwnerFilterPetDTO.fromJSON(petFilterPetDTO: PetOwnerFilterPetDTO): PetOwnerFilterPet {
    val petFilter = PetOwnerFilterPet()
    petFilter.name = petFilterPetDTO.name.toString()
    petFilter.hasMedicalShift = petFilterPetDTO.hasMedicalShift
    petFilter.hasPendingVaccine = petFilterPetDTO.hasPendingVaccine
    return petFilter
}