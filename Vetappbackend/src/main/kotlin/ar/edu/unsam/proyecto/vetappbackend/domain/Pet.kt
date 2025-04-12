package ar.edu.unsam.proyecto.vetappbackend.domain

import java.time.LocalDate


class Pet {

    var id: Int = 0
    var name: String = ""
    var age: Int = 0
    var birth: LocalDate? = null
    var sex: String = ""
    var breed: String = ""
    var specie: String = ""
    var weight: Double = 0.0
    var sterilized: Boolean = false
    var photo: String = ""
    var medicalShift: MutableList<MedicalShift> = mutableListOf<MedicalShift>()
    var pendingVaccines: MutableList<Vaccine> = mutableListOf<Vaccine>()
}


