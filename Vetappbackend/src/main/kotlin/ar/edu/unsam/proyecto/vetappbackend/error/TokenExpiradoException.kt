package ar.edu.unsam.proyecto.vetappbackend.error

class TokenExpiradoException(mensaje: String = "Token vencido") : RuntimeException(mensaje)
