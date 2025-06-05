package ar.edu.unsam.proyecto.vetappbackend.domain.mail
import java.time.*
import java.time.format.DateTimeFormatter
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.domain.type.*
import org.springframework.stereotype.Component

@Component
class MailContent {

    fun generateSubjectAndMessage(pet:Pet, hour: LocalTime, date: LocalDate, type: TypeOfNotification): Pair<String, String> {
        val hourString: String = this.hourString(hour)
        val dateString: String = this.dateString(date)
        val formattedDateTime: String = "$dateString a las $hourString hs"

        return when (type) {
            TypeOfNotification.SHIFT_REMINDER ->
                "Recordatorio de turno para ${pet.name}" to
                        """Recordatorio: la mascota "${pet.name}" tiene un turno médico hoy a las $hourString."""

            TypeOfNotification.SHIFT_CREATE ->
                "Nuevo turno para ${pet.name}" to
                        """Se ha creado con éxito un nuevo turno médico para la mascota "${pet.name}", para el día $formattedDateTime."""

            TypeOfNotification.SHIFT_UPDATE ->
                "Turno modificado para ${pet.name}" to
                        """Se ha sido modificado con éxito el turno médico de la mascota "${pet.name}", para el día $formattedDateTime."""

            TypeOfNotification.SHIFT_DELETE ->
                "Turno cancelado para ${pet.name}" to
                        """El turno médico de la mascota "${pet.name}", programado para el día $formattedDateTime, ha sido cancelado."""
        }
    }

    fun generateHtmlContent(nameRecipient: String, surnameRecipient: String, plainText: String ): String {
        val headerImageUrl = "https://i.postimg.cc/fW99KKch/logo-vet-app-horizontal-1.png"
        //"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSmYy1zpAo31nzp0H0B-rbaPrPDkCNqpPrThA&s"

        return """
            <html>
                <body style="font-family: Arial, sans-serif; color: #333; background-color: #f9f9f9; padding: 0; margin: 0;">
                    <div style="background-color: #ffffff; max-width: 600px; margin: 20px auto; border-radius: 8px; 
                        box-shadow: 0 4px 20px rgba(0,0,0,0.15); border: 1px solid #ddd; overflow: hidden;">
                        <div style="background-color: #f1f8e8; padding: 10px; text-align: center;">
                            <img src="$headerImageUrl alt="Veterinaria Logo" style="max-height: 80px;" />
                        </div>
                        <div style="padding: 30px;">
                            <h2 style="color: #55ad9b; font-size: 20px;"> ¡Hola $nameRecipient $surnameRecipient!, esperamos que estés muy bien.</h2>
                            <p style="font-size: 15px; line-height: 1.6;">$plainText</p>
                            <p style="font-size: 14px; margin-top: 30px;">Gracias por confiar en nosotros.<br>🐾 <strong> Equipo de VetApp </strong> 🐾</p>
                        </div>
                    </div>
                </body>
            </html>
        """.trimIndent()
    }


    private fun hourString(hour: LocalTime): String = hour.format(DateTimeFormatter.ofPattern("HH:mm"))

    private fun dateString(date: LocalDate): String = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    
}