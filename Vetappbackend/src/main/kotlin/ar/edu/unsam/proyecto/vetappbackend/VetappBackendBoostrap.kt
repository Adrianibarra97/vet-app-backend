package ar.edu.unsam.proyecto.vetappbackend

import ar.edu.unsam.proyecto.vetappbackend.domain.*
import ar.edu.unsam.proyecto.vetappbackend.repository.*
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class VetappBackendBoostrap: InitializingBean {
    @Autowired lateinit var userDataRepository: UserDataRepository

    @Autowired lateinit var medicalHistoryRepository: MedicalHistoryRepository

    @Autowired lateinit var petRepository: PetRepository

    @Autowired lateinit var medicalShiftRepository: MedicalShiftRepository

    @Autowired lateinit var recipeRepository: RecipeRepository

    // PetOwner
    var tamara = PetOwner()
    var ezequiel = PetOwner()
    var caroline = PetOwner()
    var lucasRdz = PetOwner()

    // PreExistingDisease
    var parvovirus = PreExistenceDisease()
    var hepatitis = PreExistenceDisease()
    var diabetes = PreExistenceDisease()
    var moquillo = PreExistenceDisease()

    // StudyResult
    var estudioParvovirus = StudyResult()
    var estudioHepatitis = StudyResult()
    var estudioDiabetes = StudyResult()
    var estudioMoquillo = StudyResult()

    // Vaccines
    var vacunaAntirrabica = Vaccine()
    var vacunaParvovirus = Vaccine()
    var vacunaHepatitis = Vaccine()
    var vacunaMoquillo = Vaccine()

    // MedicalHistory
    var medicalHistoryNala = MedicalHistory()
    var medicalHistoryOli = MedicalHistory()
    var medicalHistoryOwie = MedicalHistory()
    var medicalHistoryRocky = MedicalHistory()
    var medicalHistoryPipi = MedicalHistory()
    var medicalHistoryMorena = MedicalHistory()
    var medicalHistoryMileva = MedicalHistory()
    var medicalHistoryNapoleon = MedicalHistory()
    var medicalHistoryBurpee = MedicalHistory()
    var medicalHistoryFreya = MedicalHistory()
    var medicalHistoryCleopatra = MedicalHistory()

    // Pet
    var nala = Pet()
    var oli = Pet()
    var owie = Pet()
    var rocky = Pet()
    var pipi = Pet()
    var morena = Pet()
    var mileva = Pet()
    var napoleon = Pet()
    var burpee = Pet()
    var freya = Pet()
    var cleopatra = Pet()

    // Vet
    var adrian = Vet()
    var lucasCjs = Vet()

    // MedicalShift
    var medicalShiftNala = MedicalShift()
    var medicalShiftOli = MedicalShift()

    // Recipe
    var recipeNala = Recipe()
    var recipeOli = Recipe()


    fun createPetOwner() {
        ezequiel.apply {
            this.name = "Ezequiel"
            this.surname = "Iozzia"
            this.username = "eche"
            this.password = "eche1234"
            this.dni = 36594529
            this.email = "eze.iozzia@gmail.com"
            this.telephone = "4534-2234"
            this.address = "Olazabal 2243"
        }
        caroline.apply {
            this.name = "Caroline"
            this.surname = "Coronel"
            this.username = "caro"
            this.password = "caro1234"
            this.dni = 40567890
            this.email = "caro.coronel@gmail.com"
            this.telephone = "4834-2234"
            this.address = "Centenario 2243"
        }
        tamara.apply {
            this.name = "Tamara"
            this.surname = "Mecozzi"
            this.username = "tam"
            this.password = "tam1234"
            this.dni = 37567890
            this.email = "tam.mecozzi@gmail.com"
            this.telephone = "4739-2234"
            this.address = "Moreno 2243"
        }
        lucasRdz.apply {
            this.name = "Lucas"
            this.surname = "Rodriguez"
            this.username = "lucas"
            this.password = "lucas1234"
            this.dni = 37567890
            this.email = "lucas.rodriguez@gmail.com"
            this.telephone = "4739-2234"
            this.address = "9 de Julio 7589"
        }
        var allPetOwner = listOf(ezequiel, caroline, tamara, lucasRdz)
        userDataRepository.saveAll(allPetOwner)
    }

    fun createPreExistingDisease() {
        parvovirus.apply {
            this.name = "Parvovirus"
            this.description = "Globulos blancos defectuosos"
        }
        hepatitis.apply {
            this.name = "Hepatitis"
            this.description = "Inflamación del higado"
        }
        diabetes.apply {
            this.name = "Diabetes"
            this.description = "Glucosa alta"
        }
        moquillo.apply {
            this.name = "Moquillo"
            this.description = "Virus respiratorio, gastrointestitanl y nervioso"
        }
    }

    fun createStudyResult() {
        estudioParvovirus.apply {
            this.name = "Estudio Parvovirus"
            this.description = "Positivo"
        }
        estudioHepatitis.apply {
            this.name = "Estudio Hepatitis"
            this.description = "Positivo"
        }
        estudioDiabetes.apply {
            this.name = "Estudio Diabetes"
            this.description = "Negativo"
        }
        estudioMoquillo.apply {
            this.name = "Estudio Moquillo"
            this.description = "Negativo"
        }
    }

    fun createVaccine() {
        vacunaAntirrabica.apply {
            this.name = "Vacuna Antirrabica"
            this.description = "Vacuna contra la rabia"
            this.batchNumber = 1123444
            this.applicationDate = LocalDate.of(2024,3,14)
            this.expirationDate = LocalDate.of(2025,3,14)
            this.completed = true
        }
        vacunaParvovirus.apply {
            this.name = "Vacuna Parvovirus"
            this.description = "Vacuna contra el Parvovirus"
            this.batchNumber = 54431312
            this.applicationDate = LocalDate.of(2024,11,10)
            this.expirationDate = LocalDate.of(2025,11,10)
            this.completed = false
        }
        vacunaHepatitis.apply {
            this.name = "Vacuna Hepatitis"
            this.description = "Las vacunas contra la hepatitis A y la hepatitis B protegen contra infecciones hepáticas causadas por virus"
            this.batchNumber = 21234534
            this.applicationDate = LocalDate.of(2022,2,14)
            this.expirationDate = LocalDate.of(2023,2,14)
            this.completed = false
        }
        vacunaMoquillo.apply {
            this.name = "Vacuna Moquillo"
            this.description = "Vacuna contra el moquillo"
            this.batchNumber = 34444443
            this.applicationDate = LocalDate.of(2024,12,14)
            this.expirationDate = LocalDate.of(2025,12,14)
            this.completed = true
        }
    }

    fun createMedicalHistory() {
        this.createPreExistingDisease()
        this.createStudyResult()
        this.createVaccine()

        medicalHistoryNala.apply {
            this.notes = "Nala tiene que seguir con la dieta balanceada."
            this.preExistingDiseases = mutableListOf(diabetes, moquillo)
            this.studyResults = mutableListOf(estudioDiabetes, estudioMoquillo)
            this.vaccines = mutableListOf(vacunaMoquillo)
        }
        medicalHistoryOli.apply {
            this.notes = "Oli se encuentra bien."
            this.preExistingDiseases = mutableListOf(moquillo, parvovirus)
            this.studyResults = mutableListOf(estudioParvovirus, estudioMoquillo)
            this.vaccines = mutableListOf(vacunaMoquillo, vacunaParvovirus)
        }
        medicalHistoryOwie.apply {
            this.notes = "Owie está en excelente estado."
            this.preExistingDiseases = mutableListOf(hepatitis, moquillo)
            this.studyResults = mutableListOf(estudioHepatitis, estudioMoquillo)
            this.vaccines = mutableListOf(vacunaMoquillo)
        }
        medicalHistoryRocky.apply {
            this.notes = "Rocky está en la lona."
            this.preExistingDiseases = mutableListOf(parvovirus, moquillo, diabetes, hepatitis)
            this.studyResults = mutableListOf(estudioDiabetes, estudioMoquillo, estudioParvovirus, estudioHepatitis)
            this.vaccines = mutableListOf(vacunaMoquillo, vacunaAntirrabica, vacunaParvovirus, vacunaHepatitis)
        }
        medicalHistoryPipi.apply {
            this.notes = "Pipi está estable."
            this.preExistingDiseases = mutableListOf(moquillo)
            this.studyResults = mutableListOf(estudioMoquillo)
            this.vaccines = mutableListOf(vacunaMoquillo)
        }
        medicalHistoryMorena.apply {
            this.notes = "More se encuentra muy bien."
            this.preExistingDiseases = mutableListOf(hepatitis, moquillo, parvovirus)
            this.studyResults = mutableListOf(estudioHepatitis, estudioMoquillo, estudioParvovirus)
            this.vaccines = mutableListOf(vacunaHepatitis, vacunaMoquillo, vacunaParvovirus)
        }
        medicalHistoryMileva.apply {
            this.notes = "Mileva tiene que hacer dieta."
            this.preExistingDiseases = mutableListOf(diabetes)
            this.studyResults = mutableListOf(estudioDiabetes)
            this.vaccines = mutableListOf(vacunaMoquillo)
        }
        medicalHistoryNapoleon.apply {
            this.notes = "Napoleón hay que cortarle las uñas."
            this.preExistingDiseases = mutableListOf(diabetes)
            this.studyResults = mutableListOf(estudioDiabetes)
            this.vaccines = mutableListOf(vacunaMoquillo)
        }
        medicalHistoryBurpee.apply {
            this.notes = "Burpee se encuentra bien de salud."
            this.preExistingDiseases = mutableListOf(moquillo)
            this.studyResults = mutableListOf(estudioMoquillo)
            this.vaccines = mutableListOf(vacunaMoquillo)
        }
        medicalHistoryFreya.apply {
            this.notes = "Freya tiene que comer más."
            this.preExistingDiseases = mutableListOf(parvovirus, moquillo)
            this.studyResults = mutableListOf(estudioParvovirus, estudioMoquillo)
            this.vaccines = mutableListOf(vacunaParvovirus, vacunaMoquillo)
        }
        medicalHistoryCleopatra.apply {
            this.notes = "Cleopatra, sos una reina pero... A seguir con la dieta balanceada."
            this.preExistingDiseases = mutableListOf(moquillo)
            this.studyResults = mutableListOf(estudioMoquillo)
            this.vaccines = mutableListOf(vacunaMoquillo)
        }
        val allMedicalHistory = listOf(
            medicalHistoryNala,
            medicalHistoryBurpee,
            medicalHistoryMileva,
            medicalHistoryMorena,
            medicalHistoryRocky,
            medicalHistoryFreya,
            medicalHistoryOwie,
            medicalHistoryPipi,
            medicalHistoryNapoleon,
            medicalHistoryCleopatra,
            medicalHistoryOli
        )
        medicalHistoryRepository.saveAll(allMedicalHistory)
    }

    fun createPet() {
        nala.apply {
            this.photo = "assets/nala.jpg"
            this.name = "Nala"
            this.age = 9
            this.breed = "Mestizo"
            this.sex = "Hembra"
            this.weight = 17.0
            this.sterilized = true
            this.specie = "Perro"
            this.birth = LocalDate.of(2015,10,15)
            this.petOwner = tamara
            this.medicalHistory = medicalHistoryNala
        }
        oli.apply {
            this.photo = "assets/oli.jpeg"
            this.name = "Oli"
            this.age = 4
            this.breed = "Mestizo"
            this.sex = "Macho"
            this.weight = 14.0
            this.sterilized = true
            this.specie = "Perro"
            this.birth = LocalDate.of(2021,2,20)
            this.petOwner = ezequiel
            this.medicalHistory = medicalHistoryOli
        }
        owie.apply {
            this.photo = "assets/owie.jpg"
            this.name = "Owie"
            this.age = 13
            this.breed = "Mestizo"
            this.sex = "Hembra"
            this.weight = 15.0
            this.sterilized = true
            this.specie = "Perro"
            this.birth = LocalDate.of(2012,2,19)
            this.petOwner = caroline
            this.medicalHistory = medicalHistoryOwie
        }
        rocky.apply {
            this.photo = "assets/rocky.jpg"
            this.name = "Rocky"
            this.age = 5
            this.breed = "Mestizo"
            this.sex = "Macho"
            this.weight = 25.0
            this.sterilized = false
            this.specie = "Perro"
            this.birth = LocalDate.of(2020,7,2)
            this.petOwner = ezequiel
            this.medicalHistory = medicalHistoryRocky
        }
        pipi.apply {
            this.photo = "assets/pipi.jpg"
            this.name = "Pipi"
            this.age = 5
            this.breed = "Torcaza"
            this.sex = "Hembra"
            this.weight = 0.119
            this.sterilized = false
            this.specie = "Ave"
            this.birth = LocalDate.of(2020,1,1)
            this.petOwner = caroline
            this.medicalHistory = medicalHistoryPipi
        }
        morena.apply {
            this.photo = "assets/morena.jpg"
            this.name = "Morena"
            this.age = 14
            this.breed = "Mestizo"
            this.sex = "Hembra"
            this.weight = 15.0
            this.sterilized = true
            this.specie = "Perro"
            this.birth = LocalDate.of(2011,5,20)
            this.petOwner = lucasRdz
            this.medicalHistory = medicalHistoryMorena
        }
        mileva.apply {
            this.photo = "assets/mileva.jpg"
            this.name = "Mileva"
            this.age = 4
            this.breed = "Mestizo"
            this.sex = "Hembra"
            this.weight = 2.9
            this.sterilized = true
            this.specie = "Gato"
            this.birth = LocalDate.of(2020,9,26)
            this.petOwner = caroline
            this.medicalHistory = medicalHistoryMileva
        }
        napoleon.apply {
            this.photo = "assets/napoleon.jpg"
            this.name = "Napoleón"
            this.age = 9
            this.breed = "Mestizo"
            this.sex = "Macho"
            this.weight = 6.0
            this.sterilized = true
            this.specie = "Gato"
            this.birth = LocalDate.of(2015,9,11)
            this.petOwner = tamara
            this.medicalHistory = medicalHistoryNapoleon
        }
        burpee.apply {
            this.photo = "assets/burpee.jpg"
            this.name = "Burpee"
            this.age = 8
            this.breed = "Sharpei"
            this.sex = "Macho"
            this.weight = 19.0
            this.sterilized = true
            this.specie = "Perro"
            this.birth = LocalDate.of(2016,10,1)
            this.petOwner = tamara
            this.medicalHistory = medicalHistoryBurpee
        }
        freya.apply {
            this.photo = "assets/freya.jpg"
            this.name = "Freya"
            this.age = 5
            this.breed = "Mestizo"
            this.sex = "Hembra"
            this.weight = 5.0
            this.sterilized = true
            this.specie = "Gato"
            this.birth = LocalDate.of(2019,10,30)
            this.petOwner = tamara
            this.medicalHistory = medicalHistoryFreya
        }
        cleopatra.apply {
            this.photo = "assets/cleopatra.jpg"
            this.name = "Cleopatra"
            this.age = 5
            this.breed = "Mestizo"
            this.sex = "Hembra"
            this.weight = 4.0
            this.sterilized = true
            this.specie = "Gato"
            this.birth = LocalDate.of(2019,10,30)
            this.petOwner = tamara
            this.medicalHistory = medicalHistoryCleopatra
        }
        val allPets = listOf(
            nala,
            oli,
            owie,
            rocky,
            pipi,
            morena,
            mileva,
            napoleon,
            burpee,
            freya,
            cleopatra
        )
        petRepository.saveAll(allPets)
    }

    fun createVet() {
        adrian.apply {
            this.name = "Adrian"
            this.surname = "Ibarra"
            this.username = "birra97"
            this.password = "birra1234"
            this.licence = "123455435"
            this.speciality = "surgery"
            this.businessHours = "7 a 14 hs"
            this.professionalEmail = "adri-vetapp@gmail.com"
            this.professionalAdress = "Monroe 1243"
            this.professionalTelephone = "412344445"
            this.patients = mutableSetOf(nala,cleopatra,napoleon,burpee,freya)
        }
        lucasCjs.apply {
            this.name = "Lucas"
            this.surname = "Cejas"
            this.username = "llcejas"
            this.password = "lucas1234"
            this.licence = "1869591337"
            this.speciality = "surgery"
            this.businessHours = "7 a 24 hs"
            this.professionalEmail = "lucas-vetapp@gmail.com"
            this.professionalAdress = "25 de Mayo 7898"
            this.professionalTelephone = "412344445"
            this.patients = mutableSetOf(nala,oli,owie,napoleon,burpee,freya)
        }
        var allVet = listOf(adrian, lucasCjs)
        userDataRepository.saveAll(allVet)
    }

    fun createMedicalShift() {
        medicalShiftNala.apply {
            this.patient = nala
            this.vet = lucasCjs
            this.date = LocalDate.of(2025,4,10)
        }
        medicalShiftOli.apply {
            this.patient = oli
            this.vet = adrian
            this.date = LocalDate.of(2025,4,11)
        }
        var allMedicalShift = listOf(medicalShiftNala,medicalShiftOli)
        this.medicalShiftRepository.saveAll(allMedicalShift)
    }

    fun createRecipe() {
        recipeNala.apply {
            this.descripcion = "Tenes que aplicar la vacuna de la rabia"
            this.medicalShift = medicalShiftNala
        }
        recipeOli.apply {
            this.descripcion = "Oli tiene que bajar de peso"
            this.medicalShift = medicalShiftOli
        }
        var allRecipe = listOf(recipeNala,recipeOli)
        recipeRepository.saveAll(allRecipe)
    }

    override fun afterPropertiesSet() {
        this.createPetOwner()
        this.createMedicalHistory()
        this.createPet()
        this.createVet()
        this.createMedicalShift()
        this.createRecipe()
    }
}