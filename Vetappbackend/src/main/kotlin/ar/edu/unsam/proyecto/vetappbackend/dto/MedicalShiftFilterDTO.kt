package ar.edu.unsam.proyecto.vetappbackend.dto

class MedicalShiftFilter {
    var day: String? = null
    var today: Boolean? = null
    var thisWeek: Boolean? = null
}

data class MedicalShiftFilterDTO(
    val day: String?,
    val today: Boolean?,
    val thisWeek: Boolean?
)


fun MedicalShiftFilter.toJSON(): MedicalShiftFilterDTO {
    return MedicalShiftFilterDTO(
        day = this.day,
        today = this.today,
        thisWeek = this.thisWeek
    )
}

fun MedicalShiftFilterDTO.fromJSON(medicalShiftFilterDTO: MedicalShiftFilterDTO): MedicalShiftFilter {
    val medicalShiftFilter = MedicalShiftFilter()
    medicalShiftFilter.day = medicalShiftFilterDTO.day
    medicalShiftFilter.today = medicalShiftFilterDTO.today
    medicalShiftFilter.thisWeek = medicalShiftFilterDTO.thisWeek
    return medicalShiftFilter
}