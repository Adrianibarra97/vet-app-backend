package ar.edu.unsam.proyecto.vetappbackend.domain.notification
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

interface MailSender { fun send(to: String, subject: String, body: String) }

@Component
class ImplementationMailSender(private val newMailSender: JavaMailSender) : MailSender {

    override fun send(to: String, subject: String, body: String) {
        val message = newMailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true, "UTF-8")
        helper.setTo(to)
        helper.setSubject(subject)
        helper.setText(body, true)
        newMailSender.send(message)
    }

}