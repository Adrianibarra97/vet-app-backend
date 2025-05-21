package ar.edu.unsam.proyecto.vetappbackend.domain.notification
import java.time.*
import java.time.format.DateTimeFormatter
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.domain.type.*

class NotificationContent(val pet: Pet, val hour: LocalTime, val date: LocalDate, val type: TypeOfNotification) {

    fun generateSubjectAndMessage(): Pair<String, String> {
        return when (type) {
            TypeOfNotification.SHIFT_REMINDER ->
                "Recordatorio de turno para ${pet.name}" to
                        """Recordatorio: la mascota "${pet.name}" tiene un turno médico hoy a las ${this.hourString()}."""

            TypeOfNotification.SHIFT_CREATE ->
                "Nuevo turno para ${pet.name}" to
                        """Se ha creado con éxito un nuevo turno médico para la mascota "${pet.name}", para el día ${this.formattedDateTime()}."""

            TypeOfNotification.SHIFT_UPDATE ->
                "Turno modificado para ${pet.name}" to
                        """Se ha sido modificado con éxito el turno médico de la mascota "${pet.name}", para el día ${this.formattedDateTime()}."""

            TypeOfNotification.SHIFT_DELETE ->
                "Turno cancelado para ${pet.name}" to
                        """El turno médico de la mascota "${pet.name}", programado para el día ${this.formattedDateTime()}, ha sido cancelado."""
        }
    }

    private fun hourString(): String = hour.format(DateTimeFormatter.ofPattern("HH:mm"))

    private fun dateString(): String = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

    private fun formattedDateTime(): String = "${this.dateString()} a las ${this.hourString()} hs"

}
