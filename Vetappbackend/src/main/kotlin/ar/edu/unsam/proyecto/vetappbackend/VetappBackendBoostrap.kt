package ar.edu.unsam.proyecto.vetappbackend

import ar.edu.unsam.proyecto.vetappbackend.domain.shift.*
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.PreExistenceDisease
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import ar.edu.unsam.proyecto.vetappbackend.repository.pet.MedicalHistoryRepository
import ar.edu.unsam.proyecto.vetappbackend.repository.pet.PetRepository
import ar.edu.unsam.proyecto.vetappbackend.repository.pet.PreExistenceDiseaseRepository
import ar.edu.unsam.proyecto.vetappbackend.repository.pet.StudyResultRepository
import ar.edu.unsam.proyecto.vetappbackend.repository.pet.VaccineRepository
import ar.edu.unsam.proyecto.vetappbackend.repository.shift.MedicalShiftRepository
import ar.edu.unsam.proyecto.vetappbackend.repository.shift.RecipeRepository
import ar.edu.unsam.proyecto.vetappbackend.repository.user.PetOwnerRepository
import ar.edu.unsam.proyecto.vetappbackend.repository.user.VetRepository

import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.*

@Service
class VetappBackendBoostrap: InitializingBean {


    @Autowired
    lateinit var petOwnerRepository: PetOwnerRepository

    @Autowired
    lateinit var vetRepository: VetRepository

    @Autowired
    lateinit var petRepository: PetRepository

    @Autowired
    lateinit var medicalHistoryRepository: MedicalHistoryRepository

    @Autowired
    lateinit var preExistenceDiseaseRepository: PreExistenceDiseaseRepository

    @Autowired
    lateinit var studyResultRepository: StudyResultRepository

    @Autowired
    lateinit var vaccineRepository: VaccineRepository

    @Autowired
    lateinit var medicalShiftRepository: MedicalShiftRepository

    @Autowired
    lateinit var recipeRepository: RecipeRepository


    lateinit var authCredentials1: AuthCredentials
    lateinit var authCredentials2: AuthCredentials
    lateinit var authCredentials3: AuthCredentials
    lateinit var authCredentials4: AuthCredentials
    lateinit var authCredentials5: AuthCredentials
    lateinit var authCredentials6: AuthCredentials

    lateinit var locationInfo1: LocationInfo
    lateinit var locationInfo2: LocationInfo
    lateinit var locationInfo3: LocationInfo
    lateinit var locationInfo4: LocationInfo
    lateinit var locationInfo5: LocationInfo
    lateinit var locationInfo6: LocationInfo


    lateinit var ezequiel: PetOwner
    lateinit var caroline: PetOwner
    lateinit var lucasRdz: PetOwner
    lateinit var tamara: PetOwner
    lateinit var lucasCjs: Vet
    lateinit var adrian: Vet

    // Pet
    lateinit var nala: Pet
    lateinit var oli: Pet
    lateinit var owie: Pet
    lateinit var rocky: Pet
    lateinit var pipi: Pet
    lateinit var morena: Pet
    lateinit var mileva: Pet
    lateinit var napoleon: Pet
    lateinit var burpee: Pet
    lateinit var freya: Pet
    lateinit var cleopatra: Pet

    // MedicalHistory
    lateinit var medicalHistoryNala: MedicalHistory
    lateinit var medicalHistoryOli: MedicalHistory
    lateinit var medicalHistoryOwie: MedicalHistory
    lateinit var medicalHistoryRocky: MedicalHistory
    lateinit var medicalHistoryPipi: MedicalHistory
    lateinit var medicalHistoryMorena: MedicalHistory
    lateinit var medicalHistoryMileva: MedicalHistory
    lateinit var medicalHistoryNapoleon: MedicalHistory
    lateinit var medicalHistoryBurpee: MedicalHistory
    lateinit var medicalHistoryFreya: MedicalHistory
    lateinit var medicalHistoryCleopatra: MedicalHistory

    // PreExistingDisease
    lateinit var preExistingDiseaseNala: PreExistenceDisease
    lateinit var preExistingDiseaseOli: PreExistenceDisease
    lateinit var preExistingDiseaseOwie: PreExistenceDisease
    lateinit var preExistingDiseaseRocky: PreExistenceDisease
    lateinit var preExistingDiseasePipi: PreExistenceDisease
    lateinit var preExistingDiseaseMorena: PreExistenceDisease
    lateinit var preExistingDiseaseMileva: PreExistenceDisease
    lateinit var preExistingDiseaseNapoleon: PreExistenceDisease
    lateinit var preExistingDiseaseBurpee: PreExistenceDisease
    lateinit var preExistingDiseaseFreya: PreExistenceDisease
    lateinit var preExistingDiseaseCleopatra: PreExistenceDisease

    // StudyResult
    lateinit var studyResultNala: StudyResult
    lateinit var studyResultOli: StudyResult
    lateinit var studyResultOwie: StudyResult
    lateinit var studyResultRocky: StudyResult
    lateinit var studyResultPipi: StudyResult
    lateinit var studyResultMorena: StudyResult
    lateinit var studyResultMileva: StudyResult
    lateinit var studyResultNapoleon: StudyResult
    lateinit var studyResultBurpee: StudyResult
    lateinit var studyResultFreya: StudyResult
    lateinit var studyResultCleopatra: StudyResult

    // Vaccines
    lateinit var vaccineNala: Vaccine
    lateinit var vaccineOli: Vaccine
    lateinit var vaccineOwie: Vaccine
    lateinit var vaccineRocky: Vaccine
    lateinit var vaccinePipi: Vaccine
    lateinit var vaccineMorena: Vaccine
    lateinit var vaccineMileva: Vaccine
    lateinit var vaccineNapoleon: Vaccine
    lateinit var vaccineBurpee: Vaccine
    lateinit var vaccineFreya: Vaccine
    lateinit var vaccineCleopatra: Vaccine

    // MedicalShift
    lateinit var medicalShiftNala: MedicalShift
    lateinit var medicalShiftOli: MedicalShift
    lateinit var medicalShiftRocky: MedicalShift
    lateinit var medicalShiftMileva: MedicalShift
    lateinit var medicalShiftNapoleon: MedicalShift

    // Recipe
    lateinit var recipeNala: Recipe
    lateinit var recipeOli: Recipe
    lateinit var recipeMileva: Recipe
    lateinit var recipeNapoleon: Recipe


    fun createAuthCredentials() {
        authCredentials1 = AuthCredentials(
            password = "1234",
            username = "Eche",
            typeOfUser = TypeOfUser.PETOWNER
        )
        authCredentials2 = AuthCredentials(
            password = "1234",
            username = "Caro",
            typeOfUser = TypeOfUser.PETOWNER
        )
        authCredentials3 = AuthCredentials(
            password = "1234",
            username = "Tami",
            typeOfUser = TypeOfUser.PETOWNER
        )
        authCredentials4 = AuthCredentials(
            password = "1234",
            username = "LuckR",
            typeOfUser = TypeOfUser.PETOWNER
        )
        authCredentials5 = AuthCredentials(
            password = "123",
            username = "LuckC",
            typeOfUser = TypeOfUser.VET
        )
        authCredentials6 = AuthCredentials(
            password = "123",
            username = "Adrian",
            typeOfUser = TypeOfUser.VET
        )
    }

    fun createLocationInfo() {
        locationInfo1 = LocationInfo(
            country = "Argentina",
            province = "Buenos Aires",
            locality = "Villa Urquiza",
            postalCode = "1652",
            address = "Olazabal 2243"
        )
        locationInfo2 = LocationInfo(
            address = "Centenario 2243",
            country = "Argentina",
            province = "Buenos Aires",
            locality = "San Isidro",
            postalCode = "1640"
        )
        locationInfo3 = LocationInfo(
            address = "Moreno 2243",
            country = "Argentina",
            province = "Buenos Aires",
            locality = "Gral San Martin",
            postalCode = "1652"
        )
        locationInfo4 = LocationInfo(
            address = "9 de Julio 7589",
            country = "Argentina",
            province = "Buenos Aires",
            locality = "Los Polvorines",
            postalCode = "1652"
        )
        locationInfo5 = LocationInfo(
            address = "Eva Pero 5730",
            country = "Argentina",
            province = "Buenos Aires",
            locality = "Pablo Podesta",
            postalCode = "1652"
        )
        locationInfo6 = LocationInfo(
            address = "Los Constituyentes 5789",
            country = "Argentina",
            province = "Buenos Aires",
            locality = "Villa Puyrredon",
            postalCode = "1814",
        )
    }

    fun createPetOwner() {
        ezequiel = PetOwner().apply {
            this.dni = 36594529
            this.name = "Ezequiel"
            this.surname = "Iozzia"
            this.photo = "/src/assets/eche.jfif"
            this.email = "eze.iozzia@gmail.com"
            this.telephone = "1145340000"
            this.emergencyContactName = "Hermano de Ezze"
            this.emergencyContactPhone = "1113378995"
            this.locationInfo = locationInfo1
            this.authCredentials = authCredentials1
        }
        caroline = PetOwner().apply {
            this.dni = 40567890
            this.name = "Caroline"
            this.surname = "Coronel"
            this.photo = "/src/assets/caro.jfif"
            this.email = "caro.coronel@gmail.com"
            this.telephone = "1148340000"
            this.emergencyContactName = "Mama de Caro"
            this.emergencyContactPhone = "1113378974"
            this.locationInfo = locationInfo2
            this.authCredentials = authCredentials2
        }
        tamara = PetOwner().apply {
            this.dni = 37567890
            this.name = "Tamara"
            this.surname = "Mecozzi"
            this.photo = "/src/assets/tam.jfif"
            this.email = "tam.mecozzi@gmail.com"
            this.telephone = "1147390000"
            this.emergencyContactName = "Novio de Tamara"
            this.emergencyContactPhone = "1113378456"
            this.locationInfo = locationInfo3
            this.authCredentials = authCredentials3
        }
        lucasRdz = PetOwner().apply {
            this.dni = 44567890
            this.name = "Lucas"
            this.surname = "Rodriguez"
            this.photo = "/src/assets/LuckR.jfif"
            this.email = "lucas.rodriguez@gmail.com"
            this.telephone = "1147391111"
            this.emergencyContactName = "Papa de Lucas"
            this.emergencyContactPhone = "1113378414"
            this.locationInfo = locationInfo4
            this.authCredentials = authCredentials4
        }
        var allPetOwner: List<PetOwner> = listOf(ezequiel, caroline, tamara, lucasRdz)
        this.petOwnerRepository.saveAll(allPetOwner)
    }

    fun createVet() {
        lucasCjs = Vet().apply {
            this.dni = 44264079
            this.name = "Lucas"
            this.surname = "Cejas"
            this.photo = "/src/assets/vet.jfif"
            this.email = "lucas.cejas@gmail.com"
            this.telephone = "1147392234"
            this.email = "lucas.cejas@gmail.com"
            this.telephone = "4739-2234"

            this.licence = "1869591337"
            this.speciality = "surgery"
            this.businessHours = "7 a 24 hs"
            this.professionalEmail = "lucas-vetapp@gmail.com"
            this.professionalAddress = "Vuelta de Obligado 7898"
            this.professionalTelephone = "1169591337"
            this.professionalLocality = "Munro"
            this.professionalPostalCode = "1175"
            this.locationInfo = locationInfo5
            this.authCredentials = authCredentials5
        }
        adrian = Vet().apply {
            this.dni = 37894513
            this.name = "Adrian"
            this.surname = "Ibarra"
            this.photo = "/src/assets/adri.jfif"
            this.email = "adrian.ibarra@gmail.com"
            this.telephone = "1147391337"
            this.licence = "123455435"
            this.speciality = "surgery"
            this.businessHours = "7 a 14 hs"
            this.professionalEmail = "adri-vetapp@gmail.com"
            this.professionalAddress = "Avenida Marquez 7548"
            this.professionalTelephone = "1181591457"
            this.professionalLocality = "San Isidro"
            this.professionalPostalCode = "1175"
            this.locationInfo = locationInfo6
            this.authCredentials = authCredentials6
        }
        var allVet: List<Vet> = listOf(adrian, lucasCjs)
        vetRepository.saveAll(allVet)
    }

    fun createPet() {
        nala = Pet().apply {
            petOwner = tamara
            vets = mutableSetOf(adrian)
            age = 9
            name = "Nala"
            photo = "/src/assets/nala.jfif"
            breed = "Mestizo"
            weight = 17.0
            birth = LocalDate.of(2015, 10, 15)
            sterilized = true
            sex = TypeOfSex.Hembra
            specie = TypeOfSpecie.DOG
        }
        oli = Pet().apply {
            petOwner = ezequiel
            vets = mutableSetOf(adrian)
            age = 4
            name = "Oli"
            photo = "/src/assets/oli.jfif"
            breed = "Mestizo"
            weight = 14.0
            birth = LocalDate.of(2021, 2, 20)
            sterilized = true
            sex = TypeOfSex.Macho
            specie = TypeOfSpecie.DOG
        }
        owie = Pet().apply {
            vets = mutableSetOf(adrian)
            petOwner = caroline
            age = 13
            name = "Owie"
            photo = "/src/assets/owie.jfif"
            breed = "Mestizo"
            weight = 15.0
            birth = LocalDate.of(2012, 2, 19)
            sterilized = true
            sex = TypeOfSex.Macho
            specie = TypeOfSpecie.DOG
        }
        rocky = Pet().apply {
            vets = mutableSetOf(adrian)
            petOwner = ezequiel
            age = 5
            name = "Rocky"
            photo = "/src/assets/rocky.jfif"
            breed = "Mestizo"
            weight = 25.0
            birth = LocalDate.of(2020, 7, 2)
            sterilized = false
            sex = TypeOfSex.Macho
            specie = TypeOfSpecie.DOG
        }
        pipi = Pet().apply {
            vets = mutableSetOf(adrian)
            petOwner = caroline
            age = 5
            name = "Pipi"
            photo = "/src/assets/pipi.jfif"
            breed = "Torcaza"
            weight = 0.119
            sterilized = false
            birth = LocalDate.of(2020, 1, 1)
            sex = TypeOfSex.Hembra
            specie = TypeOfSpecie.BIRD
        }
        morena = Pet().apply {
            vets = mutableSetOf(lucasCjs)
            petOwner = lucasRdz
            age = 14
            name = "Morena"
            photo = "/src/assets/morena.jfif"
            breed = "Mestizo"
            weight = 15.0
            birth = LocalDate.of(2011, 5, 20)
            sterilized = false
            sex = TypeOfSex.Hembra
            specie = TypeOfSpecie.DOG
        }
        mileva = Pet().apply {
            vets = mutableSetOf(lucasCjs)
            petOwner = caroline
            age = 4
            name = "Mileva"
            photo = "/src/assets/mileva.jfif"
            breed = "Mestizo"
            weight = 2.9
            birth = LocalDate.of(2020, 9, 26)
            sterilized = true
            sex = TypeOfSex.Hembra
            specie = TypeOfSpecie.CAT
        }
        napoleon = Pet().apply {
            vets = mutableSetOf(lucasCjs)
            petOwner = tamara
            age = 9
            name = "Napoleón"
            photo = "/src/assets/napoleon.jfif"
            breed = "Mestizo"
            weight = 6.0
            birth = LocalDate.of(2015, 9, 11)
            sterilized = false
            sex = TypeOfSex.Macho
            specie = TypeOfSpecie.CAT
        }
        burpee = Pet().apply {
            vets = mutableSetOf(lucasCjs)
            petOwner = tamara
            age = 8
            name = "Burpee"
            photo = "/src/assets/burpee.jfif"
            breed = "Sharpei"
            weight = 19.0
            birth = LocalDate.of(2016, 10, 1)
            sterilized = false
            sex = TypeOfSex.Macho
            specie = TypeOfSpecie.DOG
        }
        freya = Pet().apply {
            vets = mutableSetOf(lucasCjs)
            petOwner = tamara
            age = 5
            name = "Freya"
            photo = "/src/assets/freya.jfif"
            breed = "Mestizo"
            weight = 5.0
            birth = LocalDate.of(2019, 10, 30)
            sterilized = false
            sex = TypeOfSex.Hembra
            specie = TypeOfSpecie.CAT
        }
        cleopatra = Pet().apply {
            vets = mutableSetOf(lucasCjs, adrian)
            petOwner = tamara
            age = 5
            name = "Cleopatra"
            photo = "/src/assets/cleopatra.jfif"
            breed = "Mestizo"
            weight = 4.0
            birth = LocalDate.of(2019, 10, 30)
            sterilized = true
            sex = TypeOfSex.Hembra
            specie = TypeOfSpecie.CAT
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
        medicalHistoryNala = MedicalHistory().apply {
            pet = nala
            createdAt = LocalDate.of(2025, 4, 20)
            updatedAt = LocalDate.now()
            summary = "Nala está mejorando."
        }
        medicalHistoryOli = MedicalHistory().apply {
            pet = oli
            createdAt = LocalDate.of(2025, 2, 20)
            updatedAt = LocalDate.now()
            summary = "Oli se encuentra bien."
        }
        medicalHistoryOwie = MedicalHistory().apply {
            pet = owie
            createdAt = LocalDate.of(2025, 3, 25)
            updatedAt = LocalDate.now()
            summary = "Owie está en excelente estado."
        }
        medicalHistoryRocky = MedicalHistory().apply {
            pet = rocky
            createdAt = LocalDate.of(2025, 3, 15)
            updatedAt = LocalDate.now()
            summary = "Rocky está en la lona."
        }
        medicalHistoryPipi = MedicalHistory().apply {
            pet = pipi
            createdAt = LocalDate.of(2025, 4, 15)
            updatedAt = LocalDate.now()
            summary = "Pipi está estable."
        }
        medicalHistoryMorena = MedicalHistory().apply {
            pet = morena
            createdAt = LocalDate.of(2025, 1, 10)
            updatedAt = LocalDate.now()
            summary = "More se encuentra muy bien."
        }
        medicalHistoryMileva = MedicalHistory().apply {
            pet = mileva
            createdAt = LocalDate.of(2025, 4, 10)
            updatedAt = LocalDate.now()
            summary = "Mileva tiene que hacer dieta."
        }
        medicalHistoryNapoleon = MedicalHistory().apply {
            pet = napoleon
            createdAt = LocalDate.of(2025, 4, 10)
            updatedAt = LocalDate.now()
            summary = "Napoleón hay que cortarle las uñas."
        }
        medicalHistoryBurpee = MedicalHistory().apply {
            pet = burpee
            createdAt = LocalDate.of(2024, 12, 10)
            updatedAt = LocalDate.now()
            summary = "Burpee se encuentra bien de salud."
        }
        medicalHistoryFreya = MedicalHistory().apply {
            pet = freya
            createdAt = LocalDate.of(2024, 12, 25)
            updatedAt = LocalDate.now()
            summary = "Freya tiene que comer más."
        }
        medicalHistoryCleopatra = MedicalHistory().apply {
            pet = cleopatra
            createdAt = LocalDate.of(2024, 7, 10)
            updatedAt = LocalDate.now()
            summary = "Cleopatra, sos una reina, venís joya."
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
        preExistingDiseaseNala = PreExistenceDisease().apply {
            medicalHistory = medicalHistoryNala
            isActive = true
            observation = "Está mejorando levemente Nala"
            diagnosisDate = LocalDate.now()
            severity = TypeOfSeverity.Moderate
            type = TypeOfPreExistinceDisease.DISTETER
        }
        preExistingDiseaseOli = PreExistenceDisease().apply {
            medicalHistory = medicalHistoryOli
            isActive = true
            observation = "Está mejorando Oli"
            diagnosisDate = LocalDate.now()
            severity = TypeOfSeverity.Stable
            type = TypeOfPreExistinceDisease.PARVOVIRUS
        }
        preExistingDiseaseOwie = PreExistenceDisease().apply {
            medicalHistory = medicalHistoryOwie
            isActive = false
            observation = "Corregido Owie"
            diagnosisDate = LocalDate.now()
            severity = TypeOfSeverity.Moderate
            type = TypeOfPreExistinceDisease.EPILEPSY
        }
        preExistingDiseaseRocky = PreExistenceDisease().apply {
            medicalHistory = medicalHistoryRocky
            isActive = false
            observation = "Está grave Rocky"
            diagnosisDate = LocalDate.now()
            severity = TypeOfSeverity.Critical
            type = TypeOfPreExistinceDisease.ASTHMA
        }
        preExistingDiseasePipi = PreExistenceDisease().apply {
            medicalHistory = medicalHistoryPipi
            isActive = true
            observation = "El estado es crítico Pipi"
            diagnosisDate = LocalDate.now()
            severity = TypeOfSeverity.Critical
            type = TypeOfPreExistinceDisease.LEUKEMIA
        }
        preExistingDiseaseMorena = PreExistenceDisease().apply {
            medicalHistory = medicalHistoryMorena
            isActive = true
            observation = "Trabajo en proceso Morena"
            diagnosisDate = LocalDate.now()
            severity = TypeOfSeverity.Moderate
            type = TypeOfPreExistinceDisease.EPILEPSY
        }
        preExistingDiseaseMileva = PreExistenceDisease().apply {
            medicalHistory = medicalHistoryMileva
            isActive = true
            observation = "Trabajando con Mileva"
            diagnosisDate = LocalDate.now()
            severity = TypeOfSeverity.Critical
            type = TypeOfPreExistinceDisease.DISTETER
        }
        preExistingDiseaseNapoleon = PreExistenceDisease().apply {
            medicalHistory = medicalHistoryNapoleon
            isActive = false
            observation = "Corregido Napoleon, todo ok"
            diagnosisDate = LocalDate.now()
            severity = TypeOfSeverity.Stable
            type = TypeOfPreExistinceDisease.DIABETES
        }
        preExistingDiseaseBurpee = PreExistenceDisease().apply {
            medicalHistory = medicalHistoryBurpee
            isActive = true
            observation = "Con tratamientos Burpee"
            diagnosisDate = LocalDate.now()
            severity = TypeOfSeverity.Critical
            type = TypeOfPreExistinceDisease.LEUKEMIA
        }
        preExistingDiseaseFreya = PreExistenceDisease().apply {
            medicalHistory = medicalHistoryFreya
            isActive = false
            observation = "Situación difícil Freya"
            diagnosisDate = LocalDate.now()
            severity = TypeOfSeverity.Critical
            type = TypeOfPreExistinceDisease.PARVOVIRUS
        }
        preExistingDiseaseCleopatra = PreExistenceDisease().apply {
            medicalHistory = medicalHistoryCleopatra
            isActive = true
            observation = "Corregido, pero con tratamiento Cleopatra"
            diagnosisDate = LocalDate.now()
            severity = TypeOfSeverity.Moderate
            type = TypeOfPreExistinceDisease.ASTHMA
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
        studyResultNala = StudyResult().apply {
            medicalHistory = medicalHistoryNala
            date = LocalDate.now()
            fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            description = "Todo salió perfecto Nala"
            type = TypeOfStudyResult.CLINICAL
        }
        studyResultOli = StudyResult().apply {
            medicalHistory = medicalHistoryOli
            date = LocalDate.now()
            fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            description = "Algo salió mal Oli"
            type = TypeOfStudyResult.GENETIC
        }
        studyResultOwie = StudyResult().apply {
            medicalHistory = medicalHistoryOwie
            date = LocalDate.now()
            fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            description = "Volver a realizar el estudio Owie"
            type = TypeOfStudyResult.PATHOLOGICAL
        }
        studyResultRocky = StudyResult().apply {
            medicalHistory = medicalHistoryRocky
            date = LocalDate.now()
            fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            description = "Consumió alguna droga Rocky"
            type = TypeOfStudyResult.PHARMACOLOGICAL
        }
        studyResultPipi = StudyResult().apply {
            medicalHistory = medicalHistoryPipi
            date = LocalDate.now()
            fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            description = "Se encuentra estresado Pipi"
            type = TypeOfStudyResult.PHYSIOLOGICAL
        }
        studyResultMorena = StudyResult().apply {
            medicalHistory = medicalHistoryMorena
            date = LocalDate.now()
            fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            description = "Todo salió perfecto Morena"
            type = TypeOfStudyResult.CLINICAL
        }
        studyResultMileva = StudyResult().apply {
            medicalHistory = medicalHistoryMileva
            date = LocalDate.now()
            fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            description = "Algo defectuoso en su gen Mileva"
            type = TypeOfStudyResult.GENETIC
        }
        studyResultNapoleon = StudyResult().apply {
            medicalHistory = medicalHistoryNapoleon
            date = LocalDate.now()
            fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            description = "Tiene un ADN alterado Napoleon"
            type = TypeOfStudyResult.PATHOLOGICAL
        }
        studyResultBurpee = StudyResult().apply {
            medicalHistory = medicalHistoryBurpee
            date = LocalDate.now()
            fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            description = "Todo salió perfecto Burpee"
            type = TypeOfStudyResult.CLINICAL
        }
        studyResultFreya = StudyResult().apply {
            medicalHistory = medicalHistoryFreya
            date = LocalDate.now()
            fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            description = "Todo salió perfecto, falta el clínico Freya"
            type = TypeOfStudyResult.PHYSIOLOGICAL
        }
        studyResultCleopatra = StudyResult().apply {
            medicalHistory = medicalHistoryCleopatra
            date = LocalDate.now()
            fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            description = "Todo salió perfecto Cleopatra"
            type = TypeOfStudyResult.PHARMACOLOGICAL
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
        vaccineNala = Vaccine().apply {
            medicalHistory = medicalHistoryNala
            description = "Vacuna contra la rabia para Nala"
            batchNumber = 1123444
            applicationDate = LocalDate.of(2024, 8, 14)
            expirationDate = LocalDate.of(2026, 8, 14)
            completed = false
            type = TypeOfVaccine.ANTIRABIES
        }
        vaccineOli = Vaccine().apply {
            medicalHistory = medicalHistoryOli
            description = "Vacuna contra el moquillo para Oli"
            batchNumber = 1123445
            applicationDate = LocalDate.of(2025, 7, 10)
            expirationDate = LocalDate.of(2026, 7, 10)
            completed = false
            type = TypeOfVaccine.DISTEMPER
        }
        vaccineOwie = Vaccine().apply {
            medicalHistory = medicalHistoryOwie
            description = "Vacuna contra el parvovirus para Owie"
            batchNumber = 1123446
            applicationDate = LocalDate.of(2025, 5, 13)
            expirationDate = LocalDate.of(2026, 5, 13)
            completed = false
            type = TypeOfVaccine.PARVOVIRUS
        }
        vaccineRocky = Vaccine().apply {
            medicalHistory = medicalHistoryRocky
            description = "Vacunado contra la hepatitis Rocky"
            batchNumber = 1123447
            applicationDate = LocalDate.of(2024, 6, 5)
            expirationDate = LocalDate.of(2025, 6, 5)
            completed = true
            type = TypeOfVaccine.HEPATITIS
        }
        vaccinePipi = Vaccine().apply {
            medicalHistory = medicalHistoryPipi
            description = "Vacunado contra la leptospirosis Pipi"
            batchNumber = 1123448
            applicationDate = LocalDate.of(2024, 7, 15)
            expirationDate = LocalDate.of(2025, 7, 15)
            completed = true
            type = TypeOfVaccine.LEPTOSPIROSIS
        }
        vaccineMorena = Vaccine().apply {
            medicalHistory = medicalHistoryMorena
            description = "Vacuna contra la parainfluenza Morena"
            batchNumber = 1123449
            applicationDate = LocalDate.of(2024, 8, 20)
            expirationDate = LocalDate.of(2025, 8, 20)
            completed = false
            type = TypeOfVaccine.PARAINFLUENZA
        }
        vaccineMileva = Vaccine().apply {
            medicalHistory = medicalHistoryMileva
            description = "Vacuna contra la rabia para Mileva"
            batchNumber = 1123450
            applicationDate = LocalDate.of(2024, 9, 10)
            expirationDate = LocalDate.of(2025, 9, 10)
            completed = true
            type = TypeOfVaccine.ANTIRABIES
        }
        vaccineNapoleon = Vaccine().apply {
            medicalHistory = medicalHistoryNapoleon
            description = "Vacuna contra el parvovirus para Napoleon"
            batchNumber = 1123451
            applicationDate = LocalDate.of(2024, 10, 2)
            expirationDate = LocalDate.of(2025, 10, 2)
            completed = true
            type = TypeOfVaccine.PARVOVIRUS
        }
        vaccineBurpee = Vaccine().apply {
            medicalHistory = medicalHistoryBurpee
            description = "Vacuna contra la leptospirosis para Burpee"
            batchNumber = 1123452
            applicationDate = LocalDate.of(2024, 11, 12)
            expirationDate = LocalDate.of(2025, 11, 12)
            completed = false
            type = TypeOfVaccine.LEPTOSPIROSIS
        }
        vaccineFreya = Vaccine().apply {
            medicalHistory = medicalHistoryFreya
            description = "Vacuna contra la hepatitis para Freya"
            batchNumber = 1123453
            applicationDate = LocalDate.of(2024, 12, 5)
            expirationDate = LocalDate.of(2025, 12, 5)
            completed = true
            type = TypeOfVaccine.HEPATITIS
        }
        vaccineCleopatra = Vaccine().apply {
            medicalHistory = medicalHistoryCleopatra
            description = "Vacuna contra el moquillo para Cleopatra"
            batchNumber = 1123454
            applicationDate = LocalDate.of(2025, 1, 8)
            expirationDate = LocalDate.of(2026, 1, 8)
            completed = false
            type = TypeOfVaccine.DISTEMPER
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

    fun createMedicalShift() {
        medicalShiftNala = MedicalShift().apply {
            this.pet = nala
            this.vet = adrian
            this.hour = LocalTime.of(11, 0)
            this.date = LocalDate.now()
        }
        medicalShiftOli = MedicalShift().apply {
            this.pet = oli
            this.vet = adrian
            this.hour = LocalTime.of(13, 0)
            this.date = LocalDate.now().plusDays(1)
        }
        medicalShiftRocky = MedicalShift().apply {
            this.pet = rocky
            this.vet = adrian
            this.hour = LocalTime.of(13, 0)
            this.date = LocalDate.now().plusMonths(1)
        }
        medicalShiftMileva = MedicalShift().apply {
            this.pet = mileva
            this.vet = lucasCjs
            this.hour = LocalTime.of(11, 0)
            this.date = LocalDate.now().plusDays(1)
        }
        medicalShiftNapoleon = MedicalShift().apply {
            this.pet = napoleon
            this.vet = lucasCjs
            this.hour = LocalTime.of(15, 0)
            this.date = LocalDate.now().plusMonths(2)
        }
        var allMedicalShift = listOf(medicalShiftNala, medicalShiftOli, medicalShiftMileva, medicalShiftNapoleon, medicalShiftRocky)
        this.medicalShiftRepository.saveAll(allMedicalShift)
    }

    fun createRecipe() {
        recipeNala = Recipe().apply {
            this.description = "Nala debe que aplicarse la vacuna de la rabia"
            this.medicalShift = medicalShiftNala
        }
        recipeOli = Recipe().apply {
            this.description = "Oli tiene que bajar de peso, y hacer ejercicio"
            this.medicalShift = medicalShiftOli
        }
        recipeMileva = Recipe().apply {
            this.description = "Mileva tiene que ponerse la pipeta para las pulgas"
            this.medicalShift = medicalShiftMileva
        }
        recipeNapoleon = Recipe().apply {
            this.description = "Napoleon tiene que limarse las uñas"
            this.medicalShift = medicalShiftNapoleon
        }
        var allRecipe = listOf(recipeNala,recipeOli)
        recipeRepository.saveAll(allRecipe)
    }

    override fun afterPropertiesSet() {
        this.createAuthCredentials()
        this.createLocationInfo()
        this.createPetOwner()
        this.createVet()

        this.createPet()
        this.createMedicalHistory()

        this.createVaccine()
        this.createStudyResult()
        this.createPreExistingDisease()

        this.createMedicalShift()
        this.createRecipe()
    }
}