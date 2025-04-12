package ar.edu.unsam.proyecto.vetappbackend.domain

import java.time.LocalDate


class Pet {

    var id: Int = 0
    var photo: String = ""
    var name: String = ""
    var age: Int = 0
    var breed: String = ""
    var sex: String = ""
    var weight: Double = 0.0
    var sterilized: Boolean = false
    var specie: String = ""
    var birth: LocalDate? = null
    var medicalShift: MutableList<MedicalShift> = mutableListOf<MedicalShift>()
    var pendingVaccines: MutableList<Vaccine> = mutableListOf<Vaccine>()
}


