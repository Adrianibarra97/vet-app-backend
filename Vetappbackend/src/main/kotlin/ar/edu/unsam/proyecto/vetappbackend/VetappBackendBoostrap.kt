package ar.edu.unsam.proyecto.vetappbackend

import ar.edu.unsam.proyecto.vetappbackend.domain.shift.*
import ar.edu.unsam.proyecto.vetappbackend.repository.*
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.PreExistenceDisease
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*

import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.*

@Service
class VetappBackendBoostrap: InitializingBean {
    @Autowired lateinit var userDataRepository: UserDataRepository

    @Autowired lateinit var medicalHistoryRepository: MedicalHistoryRepository

    @Autowired lateinit var preExistenceDiseaseRepository: PreExistenceDiseaseRepository

    @Autowired lateinit var studyResultRepository: StudyResultRepository

    @Autowired lateinit var vaccineRepository: VaccineRepository

    @Autowired lateinit var petRepository: PetRepository

    @Autowired lateinit var medicalShiftRepository: MedicalShiftRepository

    @Autowired lateinit var recipeRepository: RecipeRepository

    // PetOwner
    var tamara = PetOwner()
    var ezequiel = PetOwner()
    var caroline = PetOwner()
    var lucasRdz = PetOwner()


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

    // PreExistingDisease
    var preExistingDiseaseNala = PreExistenceDisease()
    var preExistingDiseaseOli = PreExistenceDisease()
    var preExistingDiseaseOwie = PreExistenceDisease()
    var preExistingDiseaseRocky = PreExistenceDisease()
    var preExistingDiseasePipi = PreExistenceDisease()
    var preExistingDiseaseMorena = PreExistenceDisease()
    var preExistingDiseaseMileva = PreExistenceDisease()
    var preExistingDiseaseNapoleon = PreExistenceDisease()
    var preExistingDiseaseBurpee = PreExistenceDisease()
    var preExistingDiseaseFreya = PreExistenceDisease()
    var preExistingDiseaseCleopatra = PreExistenceDisease()

    // StudyResult
    var studyResultNala = StudyResult()
    var studyResultOli = StudyResult()
    var studyResultOwie = StudyResult()
    var studyResultRocky = StudyResult()
    var studyResultPipi = StudyResult()
    var studyResultMorena = StudyResult()
    var studyResultMileva = StudyResult()
    var studyResultNapoleon = StudyResult()
    var studyResultBurpee = StudyResult()
    var studyResultFreya = StudyResult()
    var studyResultCleopatra = StudyResult()


    // Vaccines
    var vaccineNala = Vaccine()
    var vaccineOli = Vaccine()
    var vaccineOwie = Vaccine()
    var vaccineRocky = Vaccine()
    var vaccinePipi = Vaccine()
    var vaccineMorena = Vaccine()
    var vaccineMileva = Vaccine()
    var vaccineNapoleon = Vaccine()
    var vaccineBurpee = Vaccine()
    var vaccineFreya = Vaccine()
    var vaccineCleopatra = Vaccine()

    // Vet
    var adrian = Vet()
    var lucasCjs = Vet()

    // MedicalShift
    var medicalShiftNala = MedicalShift()
    var medicalShiftOli = MedicalShift()
    var medicalShiftMileva = MedicalShift()
    var medicalShiftNapoleon = MedicalShift()

    // Recipe
    var recipeNala = Recipe()
    var recipeOli = Recipe()
    var recipeMileva = Recipe()
    var recipeNapoleon = Recipe()

    fun createPetOwner() {
        ezequiel.apply {
            this.name = "Ezequiel"
            this.surname = "Iozzia"
            this.username = "eche"
            this.password = "eche1234"
            this.dni = 36594529
            this.typeOfUser = TypeOfUser.PETOWNER
            this.email = "eze.iozzia@gmail.com"
            this.telephone = "4534-2234"
            this.address = "Olazabal 2243"
            this.pets = mutableSetOf(oli,rocky)
        }
        caroline.apply {
            this.name = "Caroline"
            this.surname = "Coronel"
            this.username = "caro"
            this.password = "caro1234"
            this.typeOfUser = TypeOfUser.PETOWNER
            this.dni = 40567890
            this.email = "caro.coronel@gmail.com"
            this.telephone = "4834-2234"
            this.address = "Centenario 2243"
            this.pets = mutableSetOf(pipi,owie,mileva)
        }
        tamara.apply {
            this.name = "Tamara"
            this.surname = "Mecozzi"
            this.username = "tam"
            this.password = "tam1234"
            this.typeOfUser = TypeOfUser.PETOWNER
            this.dni = 37567890
            this.email = "tam.mecozzi@gmail.com"
            this.telephone = "4739-2234"
            this.address = "Moreno 2243"
            this.pets = mutableSetOf(cleopatra,napoleon,nala,burpee,freya)
        }
        lucasRdz.apply {
            this.name = "Lucas"
            this.surname = "Rodriguez"
            this.username = "lucas"
            this.password = "lucas1234"
            this.typeOfUser = TypeOfUser.PETOWNER
            this.dni = 37567890
            this.email = "lucas.rodriguez@gmail.com"
            this.telephone = "4739-2234"
            this.address = "9 de Julio 7589"
            this.pets = mutableSetOf(morena)
        }
        var allPetOwner = listOf(ezequiel, caroline, tamara, lucasRdz)
        userDataRepository.saveAll(allPetOwner)
    }

    fun createPet() {
        nala.apply {
            this.petOwner = tamara
            this.age = 9
            this.name = "Nala"
            this.photo = "assets/nala.jpg"
            this.breed = "Mestizo"
            this.weight = 17.0
            this.birth = LocalDate.of(2015,10,15)
            this.sex = TypeOfSex.Hembra
            this.specie = TypeOfSpecie.PERRO
        }
        oli.apply {
            this.petOwner = ezequiel
            this.age = 4
            this.name = "Oli"
            this.photo = "assets/oli.jpeg"
            this.breed = "Mestizo"
            this.weight = 14.0
            this.birth = LocalDate.of(2021,2,20)
            this.sex = TypeOfSex.Macho
            this.specie = TypeOfSpecie.PERRO
        }
        owie.apply {
            this.petOwner = caroline
            this.age = 13
            this.name = "Owie"
            this.photo = "assets/owie.jpg"
            this.breed = "Mestizo"
            this.weight = 15.0
            this.birth = LocalDate.of(2012,2,19)
            this.sex = TypeOfSex.Macho
            this.specie = TypeOfSpecie.PERRO
        }
        rocky.apply {
            this.petOwner = ezequiel
            this.age = 5
            this.name = "Rocky"
            this.photo = "assets/rocky.jpg"
            this.breed = "Mestizo"
            this.weight = 25.0
            this.birth = LocalDate.of(2020,7,2)
            this.sex = TypeOfSex.Macho
            this.specie = TypeOfSpecie.PERRO
        }
        pipi.apply {
            this.petOwner = caroline
            this.age = 5
            this.name = "Pipi"
            this.photo = "assets/pipi.jpg"
            this.breed = "Torcaza"
            this.weight = 0.119
            this.birth = LocalDate.of(2020,1,1)
            this.sex = TypeOfSex.Hembra
            this.specie = TypeOfSpecie.AVE
        }
        morena.apply {
            this.petOwner = lucasRdz
            this.age = 14
            this.name = "Morena"
            this.photo = "assets/morena.jpg"
            this.breed = "Mestizo"
            this.weight = 15.0
            this.birth = LocalDate.of(2011,5,20)
            this.sex = TypeOfSex.Hembra
            this.specie = TypeOfSpecie.PERRO
        }
        mileva.apply {
            this.petOwner = caroline
            this.age = 4
            this.name = "Mileva"
            this.photo = "assets/mileva.jpg"
            this.breed = "Mestizo"
            this.weight = 2.9
            this.birth = LocalDate.of(2020,9,26)
            this.sex = TypeOfSex.Hembra
            this.specie = TypeOfSpecie.GATO
        }
        napoleon.apply {
            this.petOwner = tamara
            this.age = 9
            this.name = "Napoleón"
            this.photo = "assets/napoleon.jpg"
            this.breed = "Mestizo"
            this.weight = 6.0
            this.birth = LocalDate.of(2015,9,11)
            this.sex = TypeOfSex.Macho
            this.specie = TypeOfSpecie.GATO
        }
        burpee.apply {
            this.petOwner = tamara
            this.age = 8
            this.name = "Burpee"
            this.photo = "assets/burpee.jpg"
            this.breed = "Sharpei"
            this.weight = 19.0
            this.birth = LocalDate.of(2016,10,1)
            this.sex = TypeOfSex.Macho
            this.specie = TypeOfSpecie.PERRO
        }
        freya.apply {
            this.petOwner = tamara
            this.age = 5
            this.name = "Freya"
            this.photo = "assets/freya.jpg"
            this.breed = "Mestizo"
            this.weight = 5.0
            this.birth = LocalDate.of(2019,10,30)
            this.sex = TypeOfSex.Hembra
            this.specie = TypeOfSpecie.GATO
        }
        cleopatra.apply {
            this.petOwner = tamara
            this.age = 5
            this.name = "Cleopatra"
            this.photo = "assets/cleopatra.jpg"
            this.breed = "Mestizo"
            this.weight = 4.0
            this.birth = LocalDate.of(2019,10,30)
            this.sex = TypeOfSex.Hembra
            this.specie = TypeOfSpecie.GATO
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

    fun createMedicalHistory() {
        medicalHistoryNala.apply {
            this.pet = nala
            this.created_at = LocalDate.of(2025,4,20)
            this.updated_at = LocalDate.now()
            this.summary = "Nala esta mejorando."
        }
        medicalHistoryOli.apply {
            this.pet = oli
            this.created_at = LocalDate.of(2025,2,20)
            this.updated_at = LocalDate.now()
            this.summary = "Oli se encuentra bien."
        }
        medicalHistoryOwie.apply {
            this.pet = owie
            this.created_at = LocalDate.of(2025,3,25)
            this.updated_at = LocalDate.now()
            this.summary = "Owie está en excelente estado."
        }
        medicalHistoryRocky.apply {
            this.pet = rocky
            this.created_at = LocalDate.of(2025,3,15)
            this.updated_at = LocalDate.now()
            this.summary = "Rocky está en la lona."
        }
        medicalHistoryPipi.apply {
            this.pet = pipi
            this.created_at = LocalDate.of(2025,4,15)
            this.updated_at = LocalDate.now()
            this.summary = "Pipi está estable."
        }
        medicalHistoryMorena.apply {
            this.pet = morena
            this.created_at = LocalDate.of(2025,1,10)
            this.updated_at = LocalDate.now()
            this.summary = "More se encuentra muy bien."
        }
        medicalHistoryMileva.apply {
            this.pet = mileva
            this.created_at = LocalDate.of(2025,4,10)
            this.updated_at = LocalDate.now()
            this.summary = "Mileva tiene que hacer dieta."
        }
        medicalHistoryNapoleon.apply {
            this.pet = napoleon
            this.created_at = LocalDate.of(2025,4,10)
            this.updated_at = LocalDate.now()
            this.summary = "Napoleón hay que cortarle las uñas."
        }
        medicalHistoryBurpee.apply {
            this.pet = burpee
            this.created_at = LocalDate.of(2024,12,10)
            this.updated_at = LocalDate.now()
            this.summary = "Burpee se encuentra bien de salud."
        }
        medicalHistoryFreya.apply {
            this.pet = freya
            this.created_at = LocalDate.of(2024,12,25)
            this.updated_at = LocalDate.now()
            this.summary = "Freya tiene que comer más."
        }
        medicalHistoryCleopatra.apply {
            this.pet = cleopatra
            this.created_at = LocalDate.of(2024,7,10)
            this.updated_at = LocalDate.now()
            this.summary = "Cleopatra, sos una reina, venis joya."
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

    fun createPreExistingDisease() {
        preExistingDiseaseNala.apply {
            this.medicalHistory = medicalHistoryNala
            this.isActive = true
            this.observation = "Esta mejorando levemente Nala"
            this.diagnosisDate = LocalDate.now()
            this.severity = TypeOfSeverity.Moderado
            this.type = TypeOfPreExistinceDisease.MOQUILLO
        }
        preExistingDiseaseOli.apply {
            this.medicalHistory = medicalHistoryOli
            this.isActive = true
            this.observation = "Esta mejorando OLi"
            this.diagnosisDate = LocalDate.now()
            this.severity = TypeOfSeverity.Estable
            this.type = TypeOfPreExistinceDisease.PARVOVIRUS
        }

        preExistingDiseaseOwie.apply {
            this.medicalHistory = medicalHistoryOwie
            this.isActive = false
            this.observation = "Corregido Owie"
            this.diagnosisDate = LocalDate.now()
            this.severity = TypeOfSeverity.Moderado
            this.type = TypeOfPreExistinceDisease.EPILEPSIA
        }
        preExistingDiseaseRocky.apply {
            this.medicalHistory = medicalHistoryRocky
            this.isActive = false
            this.observation = "Esta grave Rocky"
            this.diagnosisDate = LocalDate.now()
            this.severity = TypeOfSeverity.Grave
            this.type = TypeOfPreExistinceDisease.ASMA
        }
        preExistingDiseasePipi.apply {
            this.medicalHistory = medicalHistoryPipi
            this.isActive = true
            this.observation = "El estado es critico Pipi"
            this.diagnosisDate = LocalDate.now()
            this.severity = TypeOfSeverity.Crítico
            this.type = TypeOfPreExistinceDisease.LEUCEMIA
        }
        preExistingDiseaseMorena.apply {
            this.medicalHistory = medicalHistoryMorena
            this.isActive = true
            this.observation = "Trabajo en proceso Morena"
            this.diagnosisDate = LocalDate.now()
            this.severity = TypeOfSeverity.Moderado
            this.type = TypeOfPreExistinceDisease.EPILEPSIA
        }
        preExistingDiseaseMileva.apply {
            this.medicalHistory = medicalHistoryMileva
            this.isActive = true
            this.observation = "Trabajando con Mileva"
            this.diagnosisDate = LocalDate.now()
            this.severity = TypeOfSeverity.Grave
            this.type = TypeOfPreExistinceDisease.MOQUILLO
        }
        preExistingDiseaseNapoleon.apply {
            this.medicalHistory = medicalHistoryNapoleon
            this.isActive = false
            this.observation = "Corregido Napoleon, todo ok"
            this.diagnosisDate = LocalDate.now()
            this.severity = TypeOfSeverity.Estable
            this.type = TypeOfPreExistinceDisease.DIABETES
        }
        preExistingDiseaseBurpee.apply {
            this.medicalHistory = medicalHistoryBurpee
            this.isActive = true
            this.observation = "Con tratamientos Burpee"
            this.diagnosisDate = LocalDate.now()
            this.severity = TypeOfSeverity.Crítico
            this.type = TypeOfPreExistinceDisease.LEUCEMIA
        }
        preExistingDiseaseFreya.apply {
            this.medicalHistory = medicalHistoryFreya
            this.isActive = false
            this.observation = "Situación dificil Freya"
            this.diagnosisDate = LocalDate.now()
            this.severity = TypeOfSeverity.Grave
            this.type = TypeOfPreExistinceDisease.PARVOVIRUS
        }
        preExistingDiseaseCleopatra.apply {
            this.medicalHistory = medicalHistoryCleopatra
            this.isActive = true
            this.observation = "Corregido, pero con tratamiento Cleopatra"
            this.diagnosisDate = LocalDate.now()
            this.severity = TypeOfSeverity.Moderado
            this.type = TypeOfPreExistinceDisease.ASMA
        }
        val allPreExistenceDisease = listOf(
            preExistingDiseaseNala,
            preExistingDiseaseOli,
            preExistingDiseaseBurpee,
            preExistingDiseaseMileva,
            preExistingDiseaseMorena,
            preExistingDiseaseRocky,
            preExistingDiseaseFreya,
            preExistingDiseaseOwie,
            preExistingDiseasePipi,
            preExistingDiseaseNapoleon,
            preExistingDiseaseCleopatra
        )
        preExistenceDiseaseRepository.saveAll(allPreExistenceDisease)
    }

    fun createStudyResult() {
        studyResultNala.apply {
            this.medicalHistory = medicalHistoryNala
            this.date = LocalDate.now()
            this.fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            this.interpretation = "Todo salio perfecto Nala"
            this.type = TypeOfStudyResult.CLINICAL
        }
        studyResultOli.apply {
            this.medicalHistory = medicalHistoryOli
            this.date = LocalDate.now()
            this.fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            this.interpretation = "Algo salio mal Oli"
            this.type = TypeOfStudyResult.GENETIC
        }
        studyResultOwie.apply {
            this.medicalHistory = medicalHistoryOwie
            this.date = LocalDate.now()
            this.fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            this.interpretation = "Volver a realizar el estudio Owie"
            this.type = TypeOfStudyResult.PATHOLOGICAL
        }
        studyResultRocky.apply {
            this.medicalHistory = medicalHistoryRocky
            this.date = LocalDate.now()
            this.fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            this.interpretation = "Consumio alguna droga Rocky"
            this.type = TypeOfStudyResult.PHARMACOLOGICAL
        }
        studyResultPipi.apply {
            this.medicalHistory = medicalHistoryPipi
            this.date = LocalDate.now()
            this.fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            this.interpretation = "Se encuentra estresado Pipi"
            this.type = TypeOfStudyResult.PHYSIOLOGICAL
        }
        studyResultMorena.apply {
            this.medicalHistory = medicalHistoryMorena
            this.date = LocalDate.now()
            this.fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            this.interpretation = "Todo salio perfecto Morena"
            this.type = TypeOfStudyResult.CLINICAL
        }
        studyResultMileva.apply {
            this.medicalHistory = medicalHistoryMileva
            this.date = LocalDate.now()
            this.fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            this.interpretation = "Algo defectuoso en sun gen Mileva"
            this.type = TypeOfStudyResult.GENETIC
        }
        studyResultNapoleon.apply {
            this.medicalHistory = medicalHistoryNapoleon
            this.date = LocalDate.now()
            this.fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            this.interpretation = "Tiene un ADN alterado Napoleon"
            this.type = TypeOfStudyResult.PATHOLOGICAL
        }
        studyResultBurpee.apply {
            this.medicalHistory = medicalHistoryBurpee
            this.date = LocalDate.now()
            this.fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            this.interpretation = "Todo salio perfecto Burpee"
            this.type = TypeOfStudyResult.CLINICAL
        }
        studyResultFreya.apply {
            this.medicalHistory = medicalHistoryFreya
            this.date = LocalDate.now()
            this.fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            this.interpretation = "Todo salio perfecto, falta el clinico Freya"
            this.type = TypeOfStudyResult.PHYSIOLOGICAL
        }
        studyResultCleopatra.apply {
            this.medicalHistory = medicalHistoryCleopatra
            this.date = LocalDate.now()
            this.fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            this.interpretation = "Todo salio perfecto Cleopatra"
            this.type = TypeOfStudyResult.PHARMACOLOGICAL
        }
        val allStudyResult = listOf(
            studyResultNala,
            studyResultOli,
            studyResultOwie,
            studyResultRocky,
            studyResultPipi,
            studyResultMorena,
            studyResultMileva,
            studyResultNapoleon,
            studyResultBurpee,
            studyResultFreya,
            studyResultCleopatra
        )
        studyResultRepository.saveAll(allStudyResult)
    }

    fun createVaccine() {
        vaccineNala.apply {
            this.medicalHistory = medicalHistoryNala
            this.description = "Vacuna contra la rabia para Nala"
            this.batchNumber = 1123444
            this.applicationDate = LocalDate.of(2024, 8, 14)
            this.expirationDate = LocalDate.of(2026, 8, 14)
            this.completed = false
            this.type = TypeOfVaccine.ANTIRRÁBICA
        }
        vaccineOli.apply {
            this.medicalHistory = medicalHistoryOli
            this.description = "Vacuna contra el moquillo para Oli"
            this.batchNumber = 1123445
            this.applicationDate = LocalDate.of(2025, 7, 10)
            this.expirationDate = LocalDate.of(2026, 7, 10)
            this.completed = false
            this.type = TypeOfVaccine.MOQUILLO
        }
        vaccineOwie.apply {
            this.medicalHistory = medicalHistoryOwie
            this.description = "Vacuna contra el parvovirus para Owie"
            this.batchNumber = 1123446
            this.applicationDate = LocalDate.of(2025, 5, 13)
            this.expirationDate = LocalDate.of(2026, 5, 13)
            this.completed = false
            this.type = TypeOfVaccine.PARVOVIRUS
        }
        vaccineRocky.apply {
            this.medicalHistory = medicalHistoryRocky
            this.description = "Vacunado contra la hepatitis Rocky"
            this.batchNumber = 1123447
            this.applicationDate = LocalDate.of(2024, 6, 5)
            this.expirationDate = LocalDate.of(2025, 6, 5)
            this.completed = true
            this.type = TypeOfVaccine.HEPATITIS
        }
        vaccinePipi.apply {
            this.medicalHistory = medicalHistoryPipi
            this.description = "Vacunado contra la leptospirosis Pipi"
            this.batchNumber = 1123448
            this.applicationDate = LocalDate.of(2024, 7, 15)
            this.expirationDate = LocalDate.of(2025, 7, 15)
            this.completed = true
            this.type = TypeOfVaccine.LEPTOSPIROSIS
        }
        vaccineMorena.apply {
            this.medicalHistory = medicalHistoryMorena
            this.description = "Vacuna contra la parainfluenza Morena"
            this.batchNumber = 1123449
            this.applicationDate = LocalDate.of(2024, 8, 20)
            this.expirationDate = LocalDate.of(2025, 8, 20)
            this.completed = false
            this.type = TypeOfVaccine.PARAINFLUENZA
        }
        vaccineMileva.apply {
            this.medicalHistory = medicalHistoryMileva
            this.description = "Vacuna contra la rabia para Mileva"
            this.batchNumber = 1123450
            this.applicationDate = LocalDate.of(2024, 9, 10)
            this.expirationDate = LocalDate.of(2025, 9, 10)
            this.completed = true
            this.type = TypeOfVaccine.ANTIRRÁBICA
        }
        vaccineNapoleon.apply {
            this.medicalHistory = medicalHistoryNapoleon
            this.description = "Vacuna contra el parvovirus para Napoleon"
            this.batchNumber = 1123451
            this.applicationDate = LocalDate.of(2024, 10, 2)
            this.expirationDate = LocalDate.of(2025, 10, 2)
            this.completed = true
            this.type = TypeOfVaccine.PARVOVIRUS
        }
        vaccineBurpee.apply {
            this.medicalHistory = medicalHistoryBurpee
            this.description = "Vacuna contra la leptospirosis para Burpee"
            this.batchNumber = 1123452
            this.applicationDate = LocalDate.of(2024, 11, 12)
            this.expirationDate = LocalDate.of(2025, 11, 12)
            this.completed = false
            this.type = TypeOfVaccine.LEPTOSPIROSIS
        }
        vaccineFreya.apply {
            this.medicalHistory = medicalHistoryFreya
            this.description = "Vacuna contra la hepatitis para Freya"
            this.batchNumber = 1123453
            this.applicationDate = LocalDate.of(2024, 12, 5)
            this.expirationDate = LocalDate.of(2025, 12, 5)
            this.completed = true
            this.type = TypeOfVaccine.HEPATITIS
        }
        vaccineCleopatra.apply {
            this.medicalHistory = medicalHistoryCleopatra
            this.description = "Vacuna contra el moquillo para Cleopatra"
            this.batchNumber = 1123454
            this.applicationDate = LocalDate.of(2025, 1, 8)
            this.expirationDate = LocalDate.of(2026, 1, 8)
            this.completed = false
            this.type = TypeOfVaccine.MOQUILLO
        }
        val allVaccines = listOf(
            vaccineNala,
            vaccineOli,
            vaccineOwie,
            vaccineRocky,
            vaccinePipi,
            vaccineMorena,
            vaccineMileva,
            vaccineNapoleon,
            vaccineBurpee,
            vaccineFreya,
            vaccineCleopatra
        )
        vaccineRepository.saveAll(allVaccines)
    }

    fun createVet() {
        adrian.apply {
            this.name = "Adrian"
            this.surname = "Ibarra"
            this.username = "birra97"
            this.password = "birra1234"
            this.typeOfUser = TypeOfUser.VET
            this.licence = "123455435"
            this.speciality = "surgery"
            this.businessHours = "7 a 14 hs"
            this.professionalEmail = "adri-vetapp@gmail.com"
            this.professionalAdress = "Monroe 1243"
            this.professionalTelephone = "412344445"
            this.patients = mutableSetOf(nala, oli, owie, rocky, pipi, morena)
        }
        lucasCjs.apply {
            this.name = "Lucas"
            this.surname = "Cejas"
            this.username = "llcejas"
            this.password = "lucas1234"
            this.typeOfUser = TypeOfUser.VET
            this.licence = "1869591337"
            this.speciality = "surgery"
            this.businessHours = "7 a 24 hs"
            this.professionalEmail = "lucas-vetapp@gmail.com"
            this.professionalAdress = "25 de Mayo 7898"
            this.professionalTelephone = "412344445"
            this.patients = mutableSetOf(mileva, napoleon, burpee, freya, cleopatra)
        }
        var allVet = listOf(adrian, lucasCjs)
        userDataRepository.saveAll(allVet)
    }

    fun createMedicalShift() {
        medicalShiftNala.apply {
            this.pet = nala
            this.vet = adrian
            this.hour = LocalTime.of(11, 0)
            this.date = LocalDate.of(2025,6,10)
        }
        medicalShiftOli.apply {
            this.pet = oli
            this.vet = adrian
            this.hour = LocalTime.of(13, 0)
            this.date = LocalDate.of(2025,8,11)
        }
        medicalShiftMileva.apply {
            this.pet = mileva
            this.vet = lucasCjs
            this.hour = LocalTime.of(11, 0)
            this.date = LocalDate.of(2025,7,10)
        }
        medicalShiftNapoleon.apply {
            this.pet = napoleon
            this.vet = lucasCjs
            this.hour = LocalTime.of(15, 0)
            this.date = LocalDate.of(2025,8,11)
        }
        var allMedicalShift = listOf(medicalShiftNala, medicalShiftOli, medicalShiftMileva, medicalShiftNapoleon)
        this.medicalShiftRepository.saveAll(allMedicalShift)
    }

    fun createRecipe() {
        recipeNala.apply {
            this.descripcion = "Nala debe que aplicarse la vacuna de la rabia"
            this.medicalShift = medicalShiftNala
        }
        recipeOli.apply {
            this.descripcion = "Oli tiene que bajar de peso, y hacer ejercicio"
            this.medicalShift = medicalShiftOli
        }
        recipeMileva.apply {
            this.descripcion = "Mileva tiene que ponerse la pipeta para las pulgas"
            this.medicalShift = medicalShiftMileva
        }
        recipeNapoleon.apply {
            this.descripcion = "Napoleon tiene que limarse las uñas"
            this.medicalShift = medicalShiftNapoleon
        }
        var allRecipe = listOf(recipeNala,recipeOli)
        recipeRepository.saveAll(allRecipe)
    }

    override fun afterPropertiesSet() {
        this.createPetOwner()
        this.createPet()

        this.createMedicalHistory()
        this.createPreExistingDisease()
        this.createStudyResult()
        this.createVaccine()
        this.createVet()

        this.createMedicalShift()
        this.createRecipe()
    }
}