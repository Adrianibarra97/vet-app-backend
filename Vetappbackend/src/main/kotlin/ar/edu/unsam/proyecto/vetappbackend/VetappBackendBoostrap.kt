package ar.edu.unsam.proyecto.vetappbackend

import ar.edu.unsam.proyecto.vetappbackend.domain.*
import ar.edu.unsam.proyecto.vetappbackend.repository.*
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class VetappBackendBoostrap: InitializingBean {

    @Autowired
    private lateinit var medicalHistoryRepository: MedicalHistoryRepository

    @Autowired
    lateinit var petRepository: PetRepository

    @Autowired
    lateinit var userDataRepository: UserDataRepository

    @Autowired
    lateinit var vaccineRepository: VaccineRepository

    @Autowired
    lateinit var studyResultRepository: StudyResultRepository

    @Autowired
    lateinit var recipeRepository: RecipeRepository

    @Autowired
    lateinit var preExistenceDiseaseRepository: PreExistenceDiseaseRepository

    @Autowired
    lateinit var medicalShiftRepository: MedicalShiftRepository



    override fun afterPropertiesSet() {

        //Users
        val ezequiel: PetOwner = PetOwner().apply {
            this.name = "Ezequiel"
            this.surname = "Iozzia"
            this.dni = 36594529
            this.email = "eze.iozzia@gmail.com"
            this.telephone = "4534-2234"
            this.address = "Olazabal 2243"
            this.username = "eche"
            this.password = "eche1234"
        }

        val lucas: PetOwner = PetOwner().apply {
            this.name = "Lucas"
            this.surname = "Cejas"
            this.dni = 40567890
            this.email = "lucas.cejas@gmail.com"
            this.telephone = "4534-2234"
            this.address = "Monroe 2243"
            this.username = "lucas"
            this.password = "lucas1234"
        }

        val adrian: Vet = Vet().apply {
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

        val carolina: Vet = Vet().apply {
            this.name = "Carolina"
            this.surname = "Coronel"
            this.dni = 36548989
            this.email = "caro.coronel@gmail.com"
            this.telephone = "4354-2134"
            this.address = "Nahuel huapi 2231"
            this.username = "caro91"
            this.password = "caro1234"
            this.licence = "38129381938129"
            this.jobTelephone = "412344445"
            this.speciality = "surgery"
            this.jobAdress = "Monroe 1243"
            this.professionalEmail = "caro-vetapp@gmail.com"
            this.businessHours = "7 a 12 hs"
        }


        //PreExistingDisease
            val hepatitis = PreExistenceDisease().apply {
                this.name = "Hepatitis"
                this.description = "Inflamación del higado"
            }

            val diabetes = PreExistenceDisease().apply {
                this.name = "Diabetes"
                this.description = "Glucosa alta"
            }

            val moquillo = PreExistenceDisease().apply {
                this.name = "Moquillo"
                this.description = "Virus respiratorio, gastrointestitanl y nervioso"
            }

            val parvovirus = PreExistenceDisease().apply {
                this.name = "Parvovirus"
                this.description = "Globulos blancos defectuosos"
            }

        //StudyResult
            val estudioHepatitis = StudyResult().apply {
                this.name = "Estudio Hepatitis"
                this.description = "Positivo"
            }

            val estudioDiabetes = StudyResult().apply {
                this.name = "Estudio Diabetes"
                this.description = "Negativo"
            }

            val estudioMoquillo = StudyResult().apply {
                this.name = "Estudio Moquillo"
                this.description = "Negativo"
            }

            val estudioParvovirus = StudyResult().apply {
                this.name = "Estudio Parvovirus"
                this.description = "Positivo"
            }

        //Vaccines
            val vacunaHepatitis = Vaccine().apply {
                this.name = "Vacuna Hepatitis"
                this.description = "Las vacunas contra la hepatitis A y la hepatitis B protegen contra infecciones hepáticas causadas por virus"
                this.batchNumber = 21234534
                this.applicationDate = LocalDate.of(2022,2,14)
                this.expirationDate = LocalDate.of(2023,2,14)
                this.completed = false
            }

            val vacunaAntirrabica = Vaccine().apply {
                this.name = "Vacuna Antirrabica"
                this.description = "Vacuna contra la rabia"
                this.batchNumber = 1123444
                this.applicationDate = LocalDate.of(2024,3,14)
                this.expirationDate = LocalDate.of(2025,3,14)
                this.completed = true
            }

            val vacunaMoquillo = Vaccine().apply {
                this.name = "Vacuna Moquillo"
                this.description = "Vacuna contra el moquillo"
                this.batchNumber = 34444443
                this.applicationDate = LocalDate.of(2024,12,14)
                this.expirationDate = LocalDate.of(2025,12,14)
                this.completed = true
            }

            val vacunaParvovirus = Vaccine().apply {
                this.name = "Vacuna Parvovirus"
                this.description = "Vacuna contra el Parvovirus"
                this.batchNumber = 54431312
                this.applicationDate = LocalDate.of(2024,11,10)
                this.expirationDate = LocalDate.of(2025,11,10)
                this.completed = false
            }

        //MedicalHistory

        val medicalHistoryNala = MedicalHistory().apply {
            this.notes = "Seguir con la dieta balanceada."
            this.preExistingDisease = mutableListOf(diabetes, moquillo)
            this.studiesReuslt = mutableListOf(estudioDiabetes, estudioMoquillo)
            this.vaccines = mutableListOf(vacunaMoquillo)

            // Configurar la relación inversa en las vacunas
            //this.vaccines.forEach { vaccine -> vaccine.medicalHistory = this }
        }

        val medicalHistoryOli = MedicalHistory().apply {
            this.notes = "Oli se encuentra bien."
            this.preExistingDisease = mutableListOf(moquillo, parvovirus)
            this.studiesReuslt = mutableListOf(estudioParvovirus, estudioMoquillo)
            this.vaccines = mutableListOf(vacunaMoquillo, vacunaParvovirus)

            // Configurar la relación inversa en las vacunas
            //this.vaccines.forEach { vaccine -> vaccine.medicalHistory = this }
        }

        val medicalHistoryOwie = MedicalHistory().apply {
            this.notes = "Owie está en excelente estado."
            this.preExistingDisease = mutableListOf(hepatitis, moquillo)
            this.studiesReuslt = mutableListOf(estudioHepatitis, estudioMoquillo)
            this.vaccines = mutableListOf(vacunaMoquillo)

            // Configurar la relación inversa en las vacunas
            //this.vaccines.forEach { vaccine -> vaccine.medicalHistory = this }
        }

        val medicalHistoryRocky = MedicalHistory().apply {
            this.notes = "Rocky está en la lona."
            this.preExistingDisease = mutableListOf(parvovirus, moquillo, diabetes, hepatitis)
            this.studiesReuslt = mutableListOf(estudioDiabetes, estudioMoquillo, estudioParvovirus, estudioHepatitis)
            this.vaccines = mutableListOf(vacunaMoquillo, vacunaAntirrabica, vacunaParvovirus, vacunaHepatitis)

            // Configurar la relación inversa en las vacunas
            //this.vaccines.forEach { vaccine -> vaccine.medicalHistory = this }
        }

        val medicalHistoryPipi = MedicalHistory().apply {
            this.notes = "Pipi está estable."
            this.preExistingDisease = mutableListOf(moquillo)
            this.studiesReuslt = mutableListOf(estudioMoquillo)
            this.vaccines = mutableListOf(vacunaMoquillo)

            // Configurar la relación inversa en las vacunas
            //this.vaccines.forEach { vaccine -> vaccine.medicalHistory = this }
        }

        val medicalHistoryMorena = MedicalHistory().apply {
            this.notes = "More se encuentra muy bien."
            this.preExistingDisease = mutableListOf(hepatitis, moquillo, parvovirus)
            this.studiesReuslt = mutableListOf(estudioHepatitis, estudioMoquillo, estudioParvovirus)
            this.vaccines = mutableListOf(vacunaHepatitis, vacunaMoquillo, vacunaParvovirus)

            // Configurar la relación inversa en las vacunas
            //this.vaccines.forEach { vaccine -> vaccine.medicalHistory = this }
        }

        val medicalHistoryMileva = MedicalHistory().apply {
            this.notes = "Mileva tiene que hacer dieta."
            this.preExistingDisease = mutableListOf(diabetes)
            this.studiesReuslt = mutableListOf(estudioDiabetes)
            this.vaccines = mutableListOf(vacunaMoquillo)

            // Configurar la relación inversa en las vacunas
            //this.vaccines.forEach { vaccine -> vaccine.medicalHistory = this }
        }

        val medicalHistoryNapoleon = MedicalHistory().apply {
            this.notes = "A Napoleón hay que cortarle las uñas."
            this.preExistingDisease = mutableListOf(diabetes)
            this.studiesReuslt = mutableListOf(estudioDiabetes)
            this.vaccines = mutableListOf(vacunaMoquillo)

            // Configurar la relación inversa en las vacunas
            //this.vaccines.forEach { vaccine -> vaccine.medicalHistory = this }
        }

        val medicalHistoryBurpee = MedicalHistory().apply {
            this.notes = "Burpee se encuentra bien de salud."
            this.preExistingDisease = mutableListOf(moquillo)
            this.studiesReuslt = mutableListOf(estudioMoquillo)
            this.vaccines = mutableListOf(vacunaMoquillo)

            // Configurar la relación inversa en las vacunas
            //this.vaccines.forEach { vaccine -> vaccine.medicalHistory = this }
        }

        val medicalHistoryFreya = MedicalHistory().apply {
            this.notes = "Freya tiene que comer más."
            this.preExistingDisease = mutableListOf(parvovirus, moquillo)
            this.studiesReuslt = mutableListOf(estudioParvovirus, estudioMoquillo)
            this.vaccines = mutableListOf(vacunaParvovirus, vacunaMoquillo)

            // Configurar la relación inversa en las vacunas
            //this.vaccines.forEach { vaccine -> vaccine.medicalHistory = this }
        }

        val medicalHistoryCleopatra = MedicalHistory().apply {
            this.notes = "Seguir con la dieta balanceada."
            this.preExistingDisease = mutableListOf(moquillo)
            this.studiesReuslt = mutableListOf(estudioMoquillo)
            this.vaccines = mutableListOf(vacunaMoquillo)

            // Configurar la relación inversa en las vacunas
            //this.vaccines.forEach { vaccine -> vaccine.medicalHistory = this }
        }

        //Pets
        val nala: Pet = Pet().apply {
            this.photo = "assets/nala.jpg"
            this.name = "Nala"
            this.age = 9
            this.breed = "Mestizo"
            this.sex = "Hembra"
            this.weight = 17.0
            this.sterilized = true
            this.specie = "Perro"
            this.birth = LocalDate.of(2015,10,15)
            this.medicalHistory = medicalHistoryNala
        }

        val oli: Pet = Pet().apply {
            this.photo = "assets/oli.jpeg"
            this.name = "Oli"
            this.age = 4
            this.breed = "Mestizo"
            this.sex = "Macho"
            this.weight = 14.0
            this.sterilized = true
            this.specie = "Perro"
            this.birth = LocalDate.of(2021,2,20)
            this.medicalHistory = medicalHistoryOli
        }

        val owie: Pet = Pet().apply {
            this.photo = "assets/owie.jpg"
            this.name = "Owie"
            this.age = 13
            this.breed = "Mestizo"
            this.sex = "Hembra"
            this.weight = 15.0
            this.sterilized = true
            this.specie = "Perro"
            this.birth = LocalDate.of(2012,2,19)
            this.medicalHistory = medicalHistoryOwie
        }

        val rocky: Pet = Pet().apply {
            this.photo = "assets/rocky.jpg"
            this.name = "Rocky"
            this.age = 5
            this.breed = "Mestizo"
            this.sex = "Macho"
            this.weight = 25.0
            this.sterilized = false
            this.specie = "Perro"
            this.birth = LocalDate.of(2020,7,2)
            this.medicalHistory = medicalHistoryRocky
        }

        val pipi: Pet = Pet().apply {
            this.photo = "assets/pipi.jpg"
            this.name = "Pipi"
            this.age = 5
            this.breed = "Torcaza"
            this.sex = "Hembra"
            this.weight = 0.119
            this.sterilized = false
            this.specie = "Ave"
            this.birth = LocalDate.of(2020,1,1)
            this.medicalHistory = medicalHistoryPipi
        }

        val morena: Pet = Pet().apply {
            this.photo = "assets/morena.jpg"
            this.name = "Morena"
            this.age = 14
            this.breed = "Mestizo"
            this.sex = "Hembra"
            this.weight = 15.0
            this.sterilized = true
            this.specie = "Perro"
            this.birth = LocalDate.of(2011,5,20)
            this.medicalHistory = medicalHistoryMorena
        }

        val mileva: Pet = Pet().apply {
            this.photo = "assets/mileva.jpg"
            this.name = "Mileva"
            this.age = 4
            this.breed = "Mestizo"
            this.sex = "Hembra"
            this.weight = 2.9
            this.sterilized = true
            this.specie = "Gato"
            this.birth = LocalDate.of(2020,9,26)
            this.medicalHistory = medicalHistoryMileva
        }

        val napoleon: Pet = Pet().apply {
            this.photo = "assets/napoleon.jpg"
            this.name = "Napoleón"
            this.age = 9
            this.breed = "Mestizo"
            this.sex = "Macho"
            this.weight = 6.0
            this.sterilized = true
            this.specie = "Gato"
            this.birth = LocalDate.of(2015,9,11)
            this.medicalHistory = medicalHistoryNapoleon
        }

        val burpee: Pet = Pet().apply {
            this.photo = "assets/burpee.jpg"
            this.name = "Burpee"
            this.age = 8
            this.breed = "Sharpei"
            this.sex = "Macho"
            this.weight = 19.0
            this.sterilized = true
            this.specie = "Perro"
            this.birth = LocalDate.of(2016,10,1)
            this.medicalHistory = medicalHistoryBurpee
        }

        val freya: Pet = Pet().apply {
            this.photo = "assets/freya.jpg"
            this.name = "Freya"
            this.age = 5
            this.breed = "Mestizo"
            this.sex = "Hembra"
            this.weight = 5.0
            this.sterilized = true
            this.specie = "Gato"
            this.birth = LocalDate.of(2019,10,30)
            this.medicalHistory = medicalHistoryFreya
        }

        val cleopatra: Pet = Pet().apply {
            this.photo = "assets/cleopatra.jpg"
            this.name = "Cleopatra"
            this.age = 5
            this.breed = "Mestizo"
            this.sex = "Hembra"
            this.weight = 4.0
            this.sterilized = true
            this.specie = "Gato"
            this.birth = LocalDate.of(2019,10,30)
            this.medicalHistory = medicalHistoryCleopatra
        }


        //Recipe
            val recipeNala = Recipe().apply {
                this.descripcion = "Tenes que aplicar la vacuna de la rabia"
            }

            val recipeOli = Recipe().apply {
                this.descripcion = "Oli tiene que bajar de peso"
            }


        //MedicalShifts
        val medicalShiftNala = MedicalShift().apply {
            this.patient = nala
            this.vet = carolina
            this.petOwner = ezequiel
            this.date = LocalDate.of(2025,4,10)
            this.recipes.add(recipeNala)
            this.medicalHistory = medicalHistoryNala
        }

        val medicalShiftOli = MedicalShift().apply {
            this.patient = oli
            this.vet = adrian
            this.petOwner = lucas
            this.date = LocalDate.of(2025,4,11)
            this.recipes.add(recipeOli)
            this.medicalHistory = medicalHistoryOli
        }

        val allUsers = listOf(
            ezequiel,
            lucas,
            adrian,
            carolina
        )

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

        val allRecipe = listOf(
            recipeOli,
            recipeNala
        )

        val allMedicalShift = listOf(
            medicalShiftNala,
            medicalShiftOli
        )

        userDataRepository.saveAll(allUsers)
        medicalHistoryRepository.saveAll(allMedicalHistory)
        petRepository.saveAll(allPets)
        recipeRepository.saveAll(allRecipe)
        medicalShiftRepository.saveAll(allMedicalShift)
    }

}