package ar.edu.unsam.proyecto.vetappbackend

import ar.edu.unsam.proyecto.vetappbackend.domain.type.*
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.PreExistenceDisease
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import ar.edu.unsam.proyecto.vetappbackend.repository.pet.PetRepository
import ar.edu.unsam.proyecto.vetappbackend.repository.pet.PreExistenceDiseaseRepository
import ar.edu.unsam.proyecto.vetappbackend.repository.pet.StudyResultRepository
import ar.edu.unsam.proyecto.vetappbackend.repository.pet.VaccineRepository
import ar.edu.unsam.proyecto.vetappbackend.repository.user.MedicalShiftRepository
import ar.edu.unsam.proyecto.vetappbackend.repository.pet.RecipeRepository
import ar.edu.unsam.proyecto.vetappbackend.repository.user.AuthCredentialsRepository
import ar.edu.unsam.proyecto.vetappbackend.repository.user.LocationInfoRepository
import ar.edu.unsam.proyecto.vetappbackend.repository.user.PetOwnerRepository
import ar.edu.unsam.proyecto.vetappbackend.repository.user.UserDataRepository
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
    private lateinit var locationInfoRepository: LocationInfoRepository

    @Autowired
    private lateinit var authCredentialsRepository: AuthCredentialsRepository

    @Autowired
    lateinit var petRepository: PetRepository

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
    lateinit var medicalShiftOli: MedicalShift
    lateinit var medicalShiftRocky: MedicalShift
    lateinit var medicalShiftOwie: MedicalShift
    lateinit var medicalShiftMileva: MedicalShift
    lateinit var medicalShiftNala: MedicalShift
    lateinit var medicalShiftNapoleon: MedicalShift

    // Recipe
    lateinit var recipeNala: Recipe
    lateinit var recipeOli: Recipe
    lateinit var recipeMileva: Recipe
    lateinit var recipeNapoleon: Recipe


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
        }
        var allVet: List<Vet> = listOf(adrian, lucasCjs)
        vetRepository.saveAll(allVet)
    }

    fun createLocationInfo() {
        locationInfo1 = LocationInfo(
            country = "Argentina",
            province = "Buenos Aires",
            locality = "Villa Urquiza",
            postalCode = "1652",
            address = "Olazabal 2243",
            userData = ezequiel
        )
        locationInfo2 = LocationInfo(
            address = "Centenario 2243",
            country = "Argentina",
            province = "Buenos Aires",
            locality = "San Isidro",
            postalCode = "1640",
            userData = caroline
        )
        locationInfo3 = LocationInfo(
            address = "Moreno 2243",
            country = "Argentina",
            province = "Buenos Aires",
            locality = "Gral San Martin",
            postalCode = "1652",
            userData = tamara
        )
        locationInfo4 = LocationInfo(
            address = "9 de Julio 7589",
            country = "Argentina",
            province = "Buenos Aires",
            locality = "Los Polvorines",
            postalCode = "1652",
            userData = lucasRdz
        )
        locationInfo5 = LocationInfo(
            address = "Los Constituyentes 5789",
            country = "Argentina",
            province = "Buenos Aires",
            locality = "Villa Puyrredon",
            postalCode = "1814",
            userData = adrian
        )
        locationInfo6 = LocationInfo(
            address = "Eva Pero 5730",
            country = "Argentina",
            province = "Buenos Aires",
            locality = "Pablo Podesta",
            postalCode = "1652",
            userData = lucasCjs
        )
        val allLocationInfo = listOf(locationInfo1, locationInfo2, locationInfo3, locationInfo4, locationInfo5, locationInfo6)
        this.locationInfoRepository.saveAll(allLocationInfo)
    }

    fun createAuthCredentials() {
        authCredentials1 = AuthCredentials(
            password = "1234",
            username = "Eche",
            typeOfUser = TypeOfUser.PETOWNER,
            userData = ezequiel
        )
        authCredentials2 = AuthCredentials(
            password = "1234",
            username = "Caro",
            typeOfUser = TypeOfUser.PETOWNER,
            userData = caroline
        )
        authCredentials3 = AuthCredentials(
            password = "1234",
            username = "Tami",
            typeOfUser = TypeOfUser.PETOWNER,
            userData = tamara
        )
        authCredentials4 = AuthCredentials(
            password = "1234",
            username = "LuckR",
            typeOfUser = TypeOfUser.PETOWNER,
            userData = lucasRdz
        )
        authCredentials5 = AuthCredentials(
            password = "123",
            username = "Adrian",
            typeOfUser = TypeOfUser.VET,
            userData = adrian
        )
        authCredentials6 = AuthCredentials(
            password = "123",
            username = "LuckC",
            typeOfUser = TypeOfUser.VET,
            userData = lucasCjs
        )
        val allAuthCredentials = listOf(authCredentials1, authCredentials2, authCredentials3, authCredentials4, authCredentials5, authCredentials6)
        this.authCredentialsRepository.saveAll(allAuthCredentials)
    }


    fun createPet() {
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
        mileva = Pet().apply {
            vets = mutableSetOf(adrian)
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
        nala = Pet().apply {
            petOwner = tamara
            vets = mutableSetOf(lucasCjs)
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
            vets = mutableSetOf(lucasCjs)
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
        morena = Pet().apply {
            vets = mutableSetOf(adrian, lucasCjs)
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
        val allPets = listOf(
            oli,      // 1 - Oli
            rocky,    // 2 - Rocky
            owie,     // 3 - Owie
            pipi,     // 4 - Pipi
            mileva,   // 5 - Mileva
            nala,     // 6 - Nala
            napoleon, // 7 - Napoleón
            burpee,   // 8 - Burpee
            freya,    // 9 - Freya
            cleopatra,// 10 - Cleopatra
            morena    // 11 - Morena
        )
        petRepository.saveAll(allPets)
    }

    fun createPreExistingDisease() {
        preExistingDiseaseOli = PreExistenceDisease().apply {
            pet = oli
            isActive = true
            observation = "Está mejorando Oli"
            diagnosisDate = LocalDate.now()
            severity = TypeOfSeverity.Stable
            type = TypeOfPreExistinceDisease.PARVOVIRUS
        }

        preExistingDiseaseRocky = PreExistenceDisease().apply {
            pet = rocky
            isActive = false
            observation = "Está grave Rocky"
            diagnosisDate = LocalDate.now()
            severity = TypeOfSeverity.Critical
            type = TypeOfPreExistinceDisease.ASTHMA
        }

        preExistingDiseaseOwie = PreExistenceDisease().apply {
            pet = owie
            isActive = false
            observation = "Corregido Owie"
            diagnosisDate = LocalDate.now()
            severity = TypeOfSeverity.Moderate
            type = TypeOfPreExistinceDisease.EPILEPSY
        }

        preExistingDiseasePipi = PreExistenceDisease().apply {
            pet = pipi
            isActive = true
            observation = "El estado es crítico Pipi"
            diagnosisDate = LocalDate.now()
            severity = TypeOfSeverity.Critical
            type = TypeOfPreExistinceDisease.LEUKEMIA
        }

        preExistingDiseaseMileva = PreExistenceDisease().apply {
            pet = mileva
            isActive = true
            observation = "Trabajando con Mileva"
            diagnosisDate = LocalDate.now()
            severity = TypeOfSeverity.Critical
            type = TypeOfPreExistinceDisease.DISTETER
        }

        preExistingDiseaseNala = PreExistenceDisease().apply {
            pet = nala
            isActive = true
            observation = "Está mejorando levemente Nala"
            diagnosisDate = LocalDate.now()
            severity = TypeOfSeverity.Moderate
            type = TypeOfPreExistinceDisease.DISTETER
        }

        preExistingDiseaseNapoleon = PreExistenceDisease().apply {
            pet = napoleon
            isActive = false
            observation = "Corregido Napoleon, todo ok"
            diagnosisDate = LocalDate.now()
            severity = TypeOfSeverity.Stable
            type = TypeOfPreExistinceDisease.DIABETES
        }

        preExistingDiseaseBurpee = PreExistenceDisease().apply {
            pet = burpee
            isActive = true
            observation = "Con tratamientos Burpee"
            diagnosisDate = LocalDate.now()
            severity = TypeOfSeverity.Critical
            type = TypeOfPreExistinceDisease.LEUKEMIA
        }

        preExistingDiseaseFreya = PreExistenceDisease().apply {
            pet = freya
            isActive = false
            observation = "Situación difícil Freya"
            diagnosisDate = LocalDate.now()
            severity = TypeOfSeverity.Critical
            type = TypeOfPreExistinceDisease.PARVOVIRUS
        }

        preExistingDiseaseCleopatra = PreExistenceDisease().apply {
            pet = cleopatra
            isActive = true
            observation = "Corregido, pero con tratamiento Cleopatra"
            diagnosisDate = LocalDate.now()
            severity = TypeOfSeverity.Moderate
            type = TypeOfPreExistinceDisease.ASTHMA
        }

        preExistingDiseaseMorena = PreExistenceDisease().apply {
            pet = morena
            isActive = true
            observation = "Trabajo en proceso Morena"
            diagnosisDate = LocalDate.now()
            severity = TypeOfSeverity.Moderate
            type = TypeOfPreExistinceDisease.EPILEPSY
        }

        val allPreExistenceDisease = listOf(
            preExistingDiseaseOli,        // 1 - Oli
            preExistingDiseaseRocky,      // 2 - Rocky
            preExistingDiseaseOwie,       // 3 - Owie
            preExistingDiseasePipi,       // 4 - Pipi
            preExistingDiseaseMileva,     // 5 - Mileva
            preExistingDiseaseNala,       // 6 - Nala
            preExistingDiseaseNapoleon,   // 7 - Napoleón
            preExistingDiseaseBurpee,     // 8 - Burpee
            preExistingDiseaseFreya,      // 9 - Freya
            preExistingDiseaseCleopatra,  // 10 - Cleopatra
            preExistingDiseaseMorena      // 11 - Morena
        )

        preExistenceDiseaseRepository.saveAll(allPreExistenceDisease)
    }

    fun createStudyResult() {
        studyResultOli = StudyResult().apply {
            pet = oli
            date = LocalDate.now()
            fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            description = "Algo salió mal Oli"
            type = TypeOfStudyResult.GENETIC
        }

        studyResultRocky = StudyResult().apply {
            pet = rocky
            date = LocalDate.now()
            fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            description = "Consumió alguna droga Rocky"
            type = TypeOfStudyResult.PHARMACOLOGICAL
        }

        studyResultOwie = StudyResult().apply {
            pet = owie
            date = LocalDate.now()
            fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            description = "Volver a realizar el estudio Owie"
            type = TypeOfStudyResult.PATHOLOGICAL
        }

        studyResultPipi = StudyResult().apply {
            pet = pipi
            date = LocalDate.now()
            fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            description = "Se encuentra estresado Pipi"
            type = TypeOfStudyResult.PHYSIOLOGICAL
        }

        studyResultMileva = StudyResult().apply {
            pet = mileva
            date = LocalDate.now()
            fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            description = "Algo defectuoso en su gen Mileva"
            type = TypeOfStudyResult.GENETIC
        }

        studyResultNala = StudyResult().apply {
            pet = nala
            date = LocalDate.now()
            fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            description = "Todo salió perfecto Nala"
            type = TypeOfStudyResult.CLINICAL
        }

        studyResultNapoleon = StudyResult().apply {
            pet = napoleon
            date = LocalDate.now()
            fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            description = "Tiene un ADN alterado Napoleon"
            type = TypeOfStudyResult.PATHOLOGICAL
        }

        studyResultBurpee = StudyResult().apply {
            pet = burpee
            date = LocalDate.now()
            fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            description = "Todo salió perfecto Burpee"
            type = TypeOfStudyResult.CLINICAL
        }

        studyResultFreya = StudyResult().apply {
            pet = freya
            date = LocalDate.now()
            fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            description = "Todo salió perfecto, falta el clínico Freya"
            type = TypeOfStudyResult.PHYSIOLOGICAL
        }

        studyResultCleopatra = StudyResult().apply {
            pet = cleopatra
            date = LocalDate.now()
            fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            description = "Todo salió perfecto Cleopatra"
            type = TypeOfStudyResult.PHARMACOLOGICAL
        }

        studyResultMorena = StudyResult().apply {
            pet = morena
            date = LocalDate.now()
            fileUrl = "this@VetAppBackendBoostrap.fileUrl"
            description = "Todo salió perfecto Morena"
            type = TypeOfStudyResult.CLINICAL
        }

        val allStudyResult = listOf(
            studyResultOli,         // 1 - Oli
            studyResultRocky,       // 2 - Rocky
            studyResultOwie,        // 3 - Owie
            studyResultPipi,        // 4 - Pipi
            studyResultMileva,      // 5 - Mileva
            studyResultNala,        // 6 - Nala
            studyResultNapoleon,    // 7 - Napoleón
            studyResultBurpee,      // 8 - Burpee
            studyResultFreya,       // 9 - Freya
            studyResultCleopatra,   // 10 - Cleopatra
            studyResultMorena       // 11 - Morena
        )

        studyResultRepository.saveAll(allStudyResult)
    }

    fun createVaccine() {
        vaccineOli = Vaccine().apply {
            pet = oli
            description = "Vacuna contra el moquillo para Oli"
            batchNumber = 1123445
            applicationDate = LocalDate.of(2025, 7, 10)
            expirationDate = LocalDate.of(2026, 7, 10)
            completed = false
            type = TypeOfVaccine.DISTEMPER
        }

        vaccineRocky = Vaccine().apply {
            pet = rocky
            description = "Vacunado contra la hepatitis Rocky"
            batchNumber = 1123447
            applicationDate = LocalDate.of(2024, 6, 5)
            expirationDate = LocalDate.of(2025, 6, 5)
            completed = true
            type = TypeOfVaccine.HEPATITIS
        }

        vaccineOwie = Vaccine().apply {
            pet = owie
            description = "Vacuna contra el parvovirus para Owie"
            batchNumber = 1123446
            applicationDate = LocalDate.of(2025, 5, 13)
            expirationDate = LocalDate.of(2026, 5, 13)
            completed = false
            type = TypeOfVaccine.PARVOVIRUS
        }

        vaccinePipi = Vaccine().apply {
            pet = pipi
            description = "Vacunado contra la leptospirosis Pipi"
            batchNumber = 1123448
            applicationDate = LocalDate.of(2024, 7, 15)
            expirationDate = LocalDate.of(2025, 7, 15)
            completed = true
            type = TypeOfVaccine.LEPTOSPIROSIS
        }

        vaccineMileva = Vaccine().apply {
            pet = mileva
            description = "Vacuna contra la rabia para Mileva"
            batchNumber = 1123450
            applicationDate = LocalDate.of(2024, 9, 10)
            expirationDate = LocalDate.of(2025, 9, 10)
            completed = true
            type = TypeOfVaccine.ANTIRABIES
        }

        vaccineNala = Vaccine().apply {
            pet = nala
            description = "Vacuna contra la rabia para Nala"
            batchNumber = 1123444
            applicationDate = LocalDate.of(2024, 8, 14)
            expirationDate = LocalDate.of(2026, 8, 14)
            completed = false
            type = TypeOfVaccine.ANTIRABIES
        }

        vaccineNapoleon = Vaccine().apply {
            pet = napoleon
            description = "Vacuna contra el parvovirus para Napoleon"
            batchNumber = 1123451
            applicationDate = LocalDate.of(2024, 10, 2)
            expirationDate = LocalDate.of(2025, 10, 2)
            completed = true
            type = TypeOfVaccine.PARVOVIRUS
        }

        vaccineBurpee = Vaccine().apply {
            pet = burpee
            description = "Vacuna contra la leptospirosis para Burpee"
            batchNumber = 1123452
            applicationDate = LocalDate.of(2024, 11, 12)
            expirationDate = LocalDate.of(2025, 11, 12)
            completed = false
            type = TypeOfVaccine.LEPTOSPIROSIS
        }

        vaccineFreya = Vaccine().apply {
            pet = freya
            description = "Vacuna contra la hepatitis para Freya"
            batchNumber = 1123453
            applicationDate = LocalDate.of(2024, 12, 5)
            expirationDate = LocalDate.of(2025, 12, 5)
            completed = true
            type = TypeOfVaccine.HEPATITIS
        }

        vaccineCleopatra = Vaccine().apply {
            pet = cleopatra
            description = "Vacuna contra el moquillo para Cleopatra"
            batchNumber = 1123454
            applicationDate = LocalDate.of(2025, 1, 8)
            expirationDate = LocalDate.of(2026, 1, 8)
            completed = false
            type = TypeOfVaccine.DISTEMPER
        }

        vaccineMorena = Vaccine().apply {
            pet = morena
            description = "Vacuna contra la parainfluenza Morena"
            batchNumber = 1123449
            applicationDate = LocalDate.of(2024, 8, 20)
            expirationDate = LocalDate.of(2025, 8, 20)
            completed = false
            type = TypeOfVaccine.PARAINFLUENZA
        }

        val allVaccines = listOf(
            vaccineOli,         // 1 - Oli
            vaccineRocky,       // 2 - Rocky
            vaccineOwie,        // 3 - Owie
            vaccinePipi,        // 4 - Pipi
            vaccineMileva,      // 5 - Mileva
            vaccineNala,        // 6 - Nala
            vaccineNapoleon,    // 7 - Napoleón
            vaccineBurpee,      // 8 - Burpee
            vaccineFreya,       // 9 - Freya
            vaccineCleopatra,   // 10 - Cleopatra
            vaccineMorena       // 11 - Morena
        )

        vaccineRepository.saveAll(allVaccines)
    }


    fun createRecipe() {
        recipeOli = Recipe().apply {
            this.description = "Oli tiene que bajar de peso, y hacer ejercicio"
            this.pet = oli
            this.date = LocalDate.now().plusMonths(2)
            this.vet = adrian
        }
        recipeMileva = Recipe().apply {
            this.description = "Mileva tiene que ponerse la pipeta para las pulgas"
            this.pet = mileva
            this.date = LocalDate.now().plusDays(2)
            this.vet = adrian
        }
        recipeNala = Recipe().apply {
            this.description = "Nala debe que aplicarse la vacuna de la rabia"
            this.date = LocalDate.now()
            this.vet = lucasCjs
            this.pet = nala
        }
        recipeNapoleon = Recipe().apply {
            this.description = "Napoleon tiene que limarse las uñas"
            this.pet = napoleon
            this.date = LocalDate.now()
            this.vet = lucasCjs
        }
        val allRecipe = listOf(recipeOli, recipeMileva, recipeNala, recipeNapoleon)
        recipeRepository.saveAll(allRecipe)
    }


    fun createMedicalShift() {
        medicalShiftOli = MedicalShift().apply {
            this.pet = oli
            this.vet = adrian
            this.hour = LocalTime.of(13, 0)
            this.date = LocalDate.now()
        }
        medicalShiftRocky = MedicalShift().apply {
            this.pet = rocky
            this.vet = adrian
            this.hour = LocalTime.of(13, 0)
            this.date = LocalDate.now().plusDays(1)
        }
        medicalShiftOwie = MedicalShift().apply {
            this.pet = owie
            this.vet = adrian
            this.hour = LocalTime.of(13, 0)
            this.date = LocalDate.now().plusWeeks(1)
        }
        medicalShiftMileva = MedicalShift().apply {
            this.pet = mileva
            this.vet = adrian
            this.hour = LocalTime.of(11, 0)
            this.date = LocalDate.now().plusMonths(1)
        }
        medicalShiftNala = MedicalShift().apply {
            this.pet = nala
            this.vet = adrian
            this.hour = LocalTime.of(11, 0)
            this.date = LocalDate.now()
        }
        medicalShiftNapoleon = MedicalShift().apply {
            this.pet = napoleon
            this.vet = lucasCjs
            this.hour = LocalTime.of(15, 0)
            this.date = LocalDate.now().plusMonths(2)
        }
        var allMedicalShift = listOf(medicalShiftOli, medicalShiftRocky, medicalShiftOwie, medicalShiftMileva, medicalShiftNala, medicalShiftNapoleon)
        this.medicalShiftRepository.saveAll(allMedicalShift)
    }


    override fun afterPropertiesSet() {
        this.createPetOwner()
        this.createVet()
        this.createLocationInfo()
        this.createAuthCredentials()

        this.createPet()
        this.createVaccine()
        this.createStudyResult()
        this.createPreExistingDisease()

        this.createMedicalShift()
        this.createRecipe()
    }
}