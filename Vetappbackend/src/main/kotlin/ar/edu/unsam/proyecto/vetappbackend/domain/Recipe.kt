package ar.edu.unsam.proyecto.vetappbackend.domain

import jakarta.persistence.*

@Entity
@Table(name = "recipe")
class Recipe {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @ManyToOne
    @JoinColumn(name = "id_medical_shift", referencedColumnName = "id")
    var medicalShift: MedicalShift? = null

    var descripcion: String = ""

}