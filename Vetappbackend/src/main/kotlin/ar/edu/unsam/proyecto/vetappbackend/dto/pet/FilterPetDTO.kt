package ar.edu.unsam.proyecto.vetappbackend.dto.pet

class FilterPet {
    var name: String? = null
    var hasMedicalShift: Boolean? = null
    var hasPendingVaccine: Boolean? = null
}

data class FilterPetDTO(
    val name: String?,
    val hasMedicalShift: Boolean?,
    val hasPendingVaccine: Boolean?
)

fun FilterPet.toJSON(): FilterPetDTO {
    return FilterPetDTO(
        name = this.name.toString(),
        hasMedicalShift = this.hasMedicalShift,
        hasPendingVaccine = this.hasPendingVaccine
    )
}

fun FilterPetDTO.fromJSON(vetFilterPetDTO: FilterPetDTO): FilterPet {
    val petFilter = FilterPet()
    petFilter.name = vetFilterPetDTO.name.toString()
    petFilter.hasMedicalShift = vetFilterPetDTO.hasMedicalShift
    petFilter.hasPendingVaccine = vetFilterPetDTO.hasPendingVaccine
    return petFilter
}