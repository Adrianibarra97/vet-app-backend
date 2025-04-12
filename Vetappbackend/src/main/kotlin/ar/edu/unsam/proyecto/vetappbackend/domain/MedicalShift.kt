package ar.edu.unsam.proyecto.vetappbackend.domain

import java.time.LocalDate


class MedicalShift {

    var id: Int = 0
    var patient: Pet = Pet()
    var vet: User? = null
    var date: LocalDate? = null

}