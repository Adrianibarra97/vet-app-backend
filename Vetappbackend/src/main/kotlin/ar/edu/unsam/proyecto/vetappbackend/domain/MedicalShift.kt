package ar.edu.unsam.proyecto.vetappbackend.domain

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "Medical_shift")
class MedicalShift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @OneToOne
    var patient: Pet = Pet()

    @ManyToOne
    @JoinColumn(name = "medical_history_id")
    var medicalHistory: MedicalHistory? = null


    @ManyToOne
    @JoinColumn(name = "vet_id")
    var vet: Vet? = null

    @ManyToOne
    @JoinColumn(name = "pet_owner_id")
    var petOwner: PetOwner? = null

    var date: LocalDate? = null

    @OneToMany
    var recipes: MutableList<Recipe> = mutableListOf()



}