package ar.edu.unsam.proyecto.vetappbackend.domain

import java.time.LocalDateTime


class MedicalShift {

    var id: Int = 0
    var patient: Pet = Pet()
    var vet: User? = null
    var date: LocalDateTime = LocalDateTime.now()

}