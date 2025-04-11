package ar.edu.unsam.proyecto.vetappbackend.domain

import java.time.LocalDate

class Vaccine {
    var id: Int = 0
    var name: String = ""
    var description: String = ""
    var batchNumber: Int = 0
    var applicationDate: LocalDate = LocalDate.now()
    var expirationDate: LocalDate? = null
    var completed: Boolean = false
}