package ar.edu.unsam.proyecto.vetappbackend.domain.email

interface MailSender {
    fun send(to: String, subject: String, body: String)
}