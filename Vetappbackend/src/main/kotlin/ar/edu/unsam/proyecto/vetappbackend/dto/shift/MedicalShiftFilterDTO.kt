package ar.edu.unsam.proyecto.vetappbackend.dto.shift
import java.time.DayOfWeek
import java.time.LocalDate

data class MedicalShiftFilterDTO(
    val day: String?,
    val today: Boolean?,
    val thisWeek: Boolean?
)

class MedicalShiftFilter {
    var day: LocalDate? = null
    var today: LocalDate? = null
    var beginningOfWeek: LocalDate? = null
    var endingOfWeek: LocalDate? = null
}

fun MedicalShiftFilterDTO.fromJSON(): MedicalShiftFilter {
    val medicalShiftFilterDTO = this
    return MedicalShiftFilter().apply {
        day = convertDate(medicalShiftFilterDTO.day.toString())
        today = convertToday(medicalShiftFilterDTO.today as Boolean)
        beginningOfWeek = convertBeginningOfWeek(medicalShiftFilterDTO.thisWeek)
        endingOfWeek = convertEndingOfWeek(medicalShiftFilterDTO.thisWeek)
    }
}


private fun convertDate(day: String?): LocalDate? {
    return day?.takeIf { it.isNotBlank() }?.let { LocalDate.parse(it) }
}

private fun convertToday(isToday: Boolean?): LocalDate? {
    return if (isToday == true) LocalDate.now() else null
}

private fun convertBeginningOfWeek(isThisWeek: Boolean?): LocalDate? {
    return if (isThisWeek == true) LocalDate.now().with(DayOfWeek.MONDAY) else null
}

private fun convertEndingOfWeek(isThisWeek: Boolean?): LocalDate? {
    return if (isThisWeek == true) LocalDate.now().with(DayOfWeek.SUNDAY) else null
}
