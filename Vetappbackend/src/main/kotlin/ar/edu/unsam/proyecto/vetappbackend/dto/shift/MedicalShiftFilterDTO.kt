package ar.edu.unsam.proyecto.vetappbackend.dto.shift
import java.time.*

data class MedicalShiftFilter (
    val day: LocalDate? = null,
    val today: LocalDate? = null,
    val beginningOfWeek: LocalDate? = null,
    val endingOfWeek: LocalDate? = null
)

data class MedicalShiftFilterDTO (
    val day: String?,
    val today: Boolean?,
    val thisWeek: Boolean?
)

fun MedicalShiftFilterDTO.fromJSON(): MedicalShiftFilter {
    val medicalShiftFilterDTO = this
    val parsedDay = day?.takeIf { it.isNotBlank() }?.let { runCatching { LocalDate.parse(it) }.getOrNull() }
    val todayDate = if (medicalShiftFilterDTO.today == true) LocalDate.now() else null
    val beginning = if (medicalShiftFilterDTO.thisWeek == true) LocalDate.now().with(DayOfWeek.MONDAY) else null
    val ending = if (medicalShiftFilterDTO.thisWeek == true) LocalDate.now().with(DayOfWeek.SUNDAY) else null

    return MedicalShiftFilter(
        day = parsedDay,
        today = todayDate,
        beginningOfWeek = beginning,
        endingOfWeek = ending
    )
}