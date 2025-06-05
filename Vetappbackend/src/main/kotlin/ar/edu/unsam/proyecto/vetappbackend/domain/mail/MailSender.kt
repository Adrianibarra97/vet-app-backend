package ar.edu.unsam.proyecto.vetappbackend.domain.mail

interface MailSender {
    fun send(to: String, subject: String, body: String)
}