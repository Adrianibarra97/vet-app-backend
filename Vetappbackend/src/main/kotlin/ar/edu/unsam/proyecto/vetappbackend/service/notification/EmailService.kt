package ar.edu.unsam.proyecto.vetappbackend.service.notification
import java.time.*
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.domain.type.*
import ar.edu.unsam.proyecto.vetappbackend.domain.notification.*
import ar.edu.unsam.proyecto.vetappbackend.domain.user.PetOwner
import ar.edu.unsam.proyecto.vetappbackend.domain.user.UserData
import ar.edu.unsam.proyecto.vetappbackend.domain.user.Vet

@Service
class EmailService {

    @Autowired lateinit var mailSender: MailSender

    fun sendAppointmentNotification(pet: Pet, vet: Vet, petOwner: PetOwner, date: LocalDate, hour: LocalTime, type: TypeOfNotification) {

        val notificationContent = NotificationContent(pet, hour, date, type)

        val (subject, message) = notificationContent.generateSubjectAndMessage()

        val htmlContentOwner = this.generateHtmlContent(petOwner.name!!, petOwner.surname!!, message)

        val htmlContentVet = this.generateHtmlContent(vet.name!!, vet.surname!!, message)

        mailSender.send(vet.professionalEmail!!, subject, htmlContentVet)
        mailSender.send(petOwner.email!!, subject, htmlContentOwner)
    }

    fun sendVerificationCode(user: UserData, verificationCode: String) {

        val subject= "Código de verificación VetApp"

        val message= "Tu código de verificación es: $verificationCode"

        mailSender.send(user.email!!, subject, message)
    }

    fun updatePassword(userData: UserData) {

        val subject= "La contraseña a sido actualizada con éxito"

        val message= "Hola ${userData.name} ${userData.surname} tu contraseña de VetApp a sido actualizada con éxito "

        mailSender.send(userData.email!!, subject, message)
    }

    private fun generateHtmlContent(nameRecipient: String, surnameRecipient: String, plainText: String ): String {

        val headerImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSmYy1zpAo31nzp0H0B-rbaPrPDkCNqpPrThA&s"

        return """
            <html>
                <body style="font-family: Arial, sans-serif; color: #333; background-color: #f9f9f9; padding: 0; margin: 0;">
                    <div style="background-color: #ffffff; max-width: 600px; margin: 20px auto; border-radius: 8px; 
                                box-shadow: 0 4px 20px rgba(0,0,0,0.15); border: 1px solid #ddd; overflow: hidden;">
                        <div style="background-color: #4CAF50; padding: 10px; text-align: center;">
                            <img src="$headerImageUrl" alt="Veterinaria Logo" style="max-height: 80px;" />
                        </div>
                        <div style="padding: 30px;">
                            <h2 style="color: #4CAF50; font-size: 20px;"> ¡Hola $nameRecipient $surnameRecipient!, esperamos que estés muy bien.</h2>
                            <p style="font-size: 15px; line-height: 1.6;">$plainText</p>
                            <p style="font-size: 14px; margin-top: 30px;">Gracias por confiar en nosotros.<br>🐾 <strong> Clínica Online VetApp </strong> 🐾</p>
                        </div>
                    </div>
                </body>
            </html>
        """.trimIndent()
    }

}
