package ar.edu.unsam.proyecto.vetappbackend.dto
import java.time.LocalDate

class VetFilterPet {
    var name: String? = null
    var hasMedicalShift: Boolean? = null
    var hasPendingVaccine: Boolean? = null
}

data class VetFilterPetDTO(
    val name: String?,
    val hasMedicalShift: Boolean?,
    val hasPendingVaccine: Boolean?
)


fun VetFilterPet.toJSON(): VetFilterPetDTO {
    return VetFilterPetDTO(
        name = this.name.toString(),
        hasMedicalShift = this.hasMedicalShift,
        hasPendingVaccine = this.hasPendingVaccine
    )
}

fun VetFilterPetDTO.fromJSON(vetFilterPetDTO: VetFilterPetDTO): VetFilterPet {
    val petFilter = VetFilterPet()
    petFilter.name = vetFilterPetDTO.name.toString()
    petFilter.hasMedicalShift = vetFilterPetDTO.hasMedicalShift
    petFilter.hasPendingVaccine = vetFilterPetDTO.hasPendingVaccine
    return petFilter
}