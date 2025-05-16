package ar.edu.unsam.proyecto.vetappbackend.domain.email

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component

@Component
class AppMailSender(private val newMailSender: JavaMailSender) : MailSender {

    override fun send(to: String, subject: String, body: String) {
        val message = newMailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true)
        helper.setTo(to)
        helper.setSubject(subject)
        helper.setText(body, false)
        newMailSender.send(message)
    }

}