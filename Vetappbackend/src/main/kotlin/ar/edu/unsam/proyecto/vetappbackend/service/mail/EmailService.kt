package ar.edu.unsam.proyecto.vetappbackend.service.mail
import java.time.*
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.domain.type.*
import ar.edu.unsam.proyecto.vetappbackend.domain.user.Vet
import ar.edu.unsam.proyecto.vetappbackend.domain.user.PetOwner
import ar.edu.unsam.proyecto.vetappbackend.domain.user.UserData
import ar.edu.unsam.proyecto.vetappbackend.domain.mail.MailSender
import ar.edu.unsam.proyecto.vetappbackend.domain.mail.MailContent

@Service
class EmailService {

    @Autowired lateinit var mailSender: MailSender

    @Autowired lateinit var mailContent: MailContent

    fun sendNotificationMedicalShift(pet: Pet, vet: Vet, petOwner: PetOwner, date: LocalDate, hour: LocalTime, type: TypeOfNotification) {

        val (subject, message) = mailContent.generateSubjectAndMessage(pet, hour, date, type)

        val htmlContentVet = mailContent.generateHtmlContent(vet.name!!, vet.surname!!, message)

        val htmlContentOwner = mailContent.generateHtmlContent(petOwner.name!!, petOwner.surname!!, message)

        mailSender.send(vet.professionalEmail!!, subject, htmlContentVet)

        mailSender.send(petOwner.email!!, subject, htmlContentOwner)

    }


    fun sendVerificationCode(user: UserData, verificationCode: String) {

        val subject= "Código de verificación VetApp"

        val message= "Tu código de verificación es: $verificationCode"

        mailSender.send(user.email!!, subject, message)
    }

    fun sendUpdatePassword(userData: UserData) {

        val subject= "La contraseña a sido actualizada con éxito"

        val message= "Hola ${userData.name} ${userData.surname} tu contraseña de VetApp a sido actualizada con éxito "

        mailSender.send(userData.email!!, subject, message)
    }

}
