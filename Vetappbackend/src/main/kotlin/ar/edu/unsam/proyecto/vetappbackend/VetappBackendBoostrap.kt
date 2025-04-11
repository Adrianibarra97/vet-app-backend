package ar.edu.unsam.proyecto.vetappbackend

import ar.edu.unsam.proyecto.vetappbackend.domain.*
import ar.edu.unsam.proyecto.vetappbackend.repository.PetRepository
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class VetappBackendBoostrap: InitializingBean {

    @Autowired
    lateinit var petRepository: PetRepository

    override fun afterPropertiesSet() {

        //Users
        val ezequiel: PetOwner = PetOwner().apply {
            this.id = 1
            this.name = "Ezequiel"
            this.surname = "Iozzia"
            this.dni = 36594529
            this.email = "eze.iozzia@gmail.com"
            this.telephone = "4534-2234"
            this.address = "Olazabal 2243"
            this.username = "eche"
            this.password = "eche1234"
        }

        val adrian: Vet = Vet().apply {
            this.id = 1
            this.name = "Adrian"
            this.surname = "Ibarra"
            this.dni = 40333221
            this.email = "adri.ibirra@gmail.com"
            this.telephone = "4354-2134"
            this.address = "Blanco Encalada 1122"
            this.username = "birra97"
            this.password = "birra1234"
            this.licence = "123455435"
            this.jobTelephone = "412344445"
            this.speciality = "surgery"
            this.jobAdress = "Monroe 1243"
            this.professionalEmail = "adri-vetapp@gmail.com"
            this.businessHours = "7 a 14 hs"
        }

        //Pets
        val oli: Pet = Pet().apply {
            this.id = 1
            this.name = "Oli"
            this.age = 4
            this.birth = LocalDateTime.of(2021,2,13,19,0,0)
            this.sex = "Macho"
            this.breed = "Mestizo"
            this.specie = "Perro"
            this.weight = 14.0
            this.sterilized = true
            this.photo = "assets/oli.jpeg"
        }

        val noah: Pet = Pet().apply {
            this.id = 2
            this.name = "Noah"
            this.age = 5
            this.birth = LocalDateTime.of(2020,3,10,19,0,0)
            this.sex = "Macho"
            this.breed = "Mestizo"
            this.specie = "Perro"
            this.weight = 25.5
            this.sterilized = true
            this.photo = "assets/noah.jpg"
        }

        //MedicalShifts
        val shift1 = MedicalShift().apply {
            this.id = 1
            this.patient = oli
            this.vet = ezequiel
            this.date = LocalDateTime.of(2025,4,10,0,0)
        }

        oli.medicalShift.add(shift1)

        val shift2 = MedicalShift().apply {
            this.id = 2
            this.patient = noah
            this.vet = adrian
            this.date = LocalDateTime.of(2025,4,11,0,0,0)
        }

        noah.medicalShift.add(shift2)


        //Repositorios
        petRepository.saveAll(listOf(oli, noah))
        petRepository.saveMedicalShift(shift1)
        petRepository.saveMedicalShift(shift2)

    }
}