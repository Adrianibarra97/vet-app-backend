package ar.edu.unsam.proyecto.vetappbackend

import ar.edu.unsam.proyecto.vetappbackend.domain.*
import ar.edu.unsam.proyecto.vetappbackend.repository.PetRepository
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

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
        val nala: Pet = Pet().apply {
            this.id = 0
            this.photo = "assets/nala.jpg"
            this.name = "Nala"
            this.age = 9
            this.breed = "Mestizo"
            this.sex = "Hembra"
            this.weight = 17.0
            this.sterilized = true
            this.specie = "Perro"
            this.birth = LocalDate.of(2015,10,15)
        }

        val oli: Pet = Pet().apply {
            this.id = 1
            this.photo = "assets/oli.jpeg"
            this.name = "Oli"
            this.age = 4
            this.breed = "Mestizo"
            this.sex = "Macho"
            this.weight = 14.0
            this.sterilized = true
            this.specie = "Perro"
            this.birth = LocalDate.of(2021,2,20)
        }

        val owie: Pet = Pet().apply {
            this.id = 2
            this.photo = "assets/owie.jpg"
            this.name = "Owie"
            this.age = 13
            this.breed = "Mestizo"
            this.sex = "Hembra"
            this.weight = 15.0
            this.sterilized = true
            this.specie = "Perro"
            this.birth = LocalDate.of(2012,2,19)
        }

        val rocky: Pet = Pet().apply {
            this.id = 3
            this.photo = "assets/rocky.jpg"
            this.name = "Rocky"
            this.age = 5
            this.breed = "Mestizo"
            this.sex = "Macho"
            this.weight = 25.0
            this.sterilized = false
            this.specie = "Perro"
            this.birth = LocalDate.of(2020,7,2)
        }

        val pipi: Pet = Pet().apply {
            this.id = 4
            this.photo = "assets/pipi.jpg"
            this.name = "Pipi"
            this.age = 5
            this.breed = "Torcaza"
            this.sex = "Hembra"
            this.weight = 0.119
            this.sterilized = false
            this.specie = "Ave"
            this.birth = LocalDate.of(2020,1,1)
        }

        val morena: Pet = Pet().apply {
            this.id = 5
            this.photo = "assets/morena.jpg"
            this.name = "Morena"
            this.age = 14
            this.breed = "Mestizo"
            this.sex = "Hembra"
            this.weight = 15.0
            this.sterilized = true
            this.specie = "Perro"
            this.birth = LocalDate.of(2011,5,20)
        }

        val mileva: Pet = Pet().apply {
            this.id = 6
            this.photo = "assets/mileva.jpg"
            this.name = "Mileva"
            this.age = 4
            this.breed = "Mestizo"
            this.sex = "Hembra"
            this.weight = 2.9
            this.sterilized = true
            this.specie = "Gato"
            this.birth = LocalDate.of(2020,9,26)
        }

        val napoleon: Pet = Pet().apply {
            this.id = 7
            this.photo = "assets/napoleon.jpg"
            this.name = "Napoleón"
            this.age = 9
            this.breed = "Mestizo"
            this.sex = "Macho"
            this.weight = 6.0
            this.sterilized = true
            this.specie = "Gato"
            this.birth = LocalDate.of(2015,9,11)
        }

        val burpee: Pet = Pet().apply {
            this.id = 8
            this.photo = "assets/burpee.jpg"
            this.name = "Burpee"
            this.age = 8
            this.breed = "Sharpei"
            this.sex = "Macho"
            this.weight = 19.0
            this.sterilized = true
            this.specie = "Perro"
            this.birth = LocalDate.of(2016,10,1)
        }

        val freya: Pet = Pet().apply {
            this.id = 9
            this.photo = "assets/freya.jpg"
            this.name = "Freya"
            this.age = 5
            this.breed = "Mestizo"
            this.sex = "Hembra"
            this.weight = 5.0
            this.sterilized = true
            this.specie = "Gato"
            this.birth = LocalDate.of(2019,10,30)
        }

        val cleopatra: Pet = Pet().apply {
            this.id = 10
            this.photo = "assets/cleopatra.jpg"
            this.name = "Cleopatra"
            this.age = 5
            this.breed = "Mestizo"
            this.sex = "Hembra"
            this.weight = 4.0
            this.sterilized = true
            this.specie = "Gato"
            this.birth = LocalDate.of(2019,10,30)
        }

        // Vaccines
        val vaccine1 = Vaccine().apply {
            this.id = 1
            this.name = "Rabia"
            this.completed = false // Vacuna pendiente
        }

        val vaccine2 = Vaccine().apply {
            this.id = 2
            this.name = "Desparasitaria"
            this.completed = true // Vacuna completada
        }

        val vaccine3 = Vaccine().apply {
            this.id = 3
            this.name = "Parvovirus"
            this.completed = false
        }

        val vaccine4 = Vaccine().apply {
            this.id = 4
            this.name = "Hepatitis"
            this.completed = true
        }

        nala.pendingVaccines.addAll(listOf(vaccine1,vaccine4))
        oli.pendingVaccines.addAll(listOf(vaccine1, vaccine2))
        owie.pendingVaccines.addAll(listOf(vaccine3,vaccine4))
        rocky.pendingVaccines.addAll(listOf(vaccine3,vaccine2))
        pipi.pendingVaccines.addAll(listOf(vaccine2,vaccine4))
        morena.pendingVaccines.addAll(listOf(vaccine2,vaccine4))
        mileva.pendingVaccines.addAll(listOf(vaccine1))
        napoleon.pendingVaccines.addAll(listOf(vaccine2))
        burpee.pendingVaccines.addAll(listOf(vaccine3))
        freya.pendingVaccines.addAll(listOf(vaccine4))
        cleopatra.pendingVaccines.addAll(listOf(vaccine4,vaccine2,vaccine3))

        //MedicalShifts
        val shift1 = MedicalShift().apply {
            this.id = 1
            this.patient = oli
            this.vet = ezequiel
            this.date = LocalDate.of(2025,4,10)
        }

        val shift2 = MedicalShift().apply {
            this.id = 2
            this.patient = owie
            this.vet = adrian
            this.date = LocalDate.of(2025,4,11)
        }

        oli.medicalShift.add(shift1)
        owie.medicalShift.add(shift2)


        //Repositorios
        petRepository.saveAll(listOf(nala,oli,owie,rocky,pipi,morena,mileva,napoleon,burpee,freya,cleopatra,))
        petRepository.saveMedicalShift(shift1)
        petRepository.saveMedicalShift(shift2)

    }
}