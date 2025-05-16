package ar.edu.unsam.proyecto.vetappbackend.service.user
import ar.edu.unsam.proyecto.vetappbackend.domain.email.AppMailSender
import ar.edu.unsam.proyecto.vetappbackend.domain.email.MailSender
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmailService {

    @Autowired lateinit var mailSender: MailSender

    fun sendAppointmentCancellation(
        vetEmail: String,
        ownerEmail: String,
        petName: String,
        appointmentDateTime: String
    ) {
        val subject = "Turno cancelado para $petName"
        val message = """
            El turno médico de la mascota "$petName", programado para $appointmentDateTime, ha sido cancelado.
            Por favor, comuníquese con la clínica veterinaria si desea reprogramarlo.
        """.trimIndent()
        mailSender.send(vetEmail, subject, message)
        mailSender.send(ownerEmail, subject, message)
    }

}