package ar.edu.unsam.proyecto.vetappbackend.domain.mail
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

@Component
class MailSenderImplementation(private val newMailSender: JavaMailSender) : MailSender {

    override fun send(to: String, subject: String, body: String) {
        val message = newMailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true, "UTF-8")
        helper.setTo(to)
        helper.setSubject(subject)
        helper.setText(body, true)
        newMailSender.send(message)
    }

}