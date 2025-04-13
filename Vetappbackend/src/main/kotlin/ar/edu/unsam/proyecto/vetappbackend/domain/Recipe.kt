package ar.edu.unsam.proyecto.vetappbackend.domain

import jakarta.persistence.*

@Entity
@Table(name = "Recipe")
class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
    var descripcion: String = ""

}