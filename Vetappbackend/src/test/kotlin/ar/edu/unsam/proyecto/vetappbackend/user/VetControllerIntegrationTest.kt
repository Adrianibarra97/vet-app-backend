package ar.edu.unsam.proyecto.vetappbackend.user

import ar.edu.unsam.proyecto.vetappbackend.domain.notification.Notification
import ar.edu.unsam.proyecto.vetappbackend.domain.type.TypeOfNotification
import ar.edu.unsam.proyecto.vetappbackend.domain.type.TypeOfUser
import ar.edu.unsam.proyecto.vetappbackend.domain.user.AuthCredentials
import ar.edu.unsam.proyecto.vetappbackend.domain.user.LocationInfo
import ar.edu.unsam.proyecto.vetappbackend.dto.user.VetDTO
import ar.edu.unsam.proyecto.vetappbackend.dto.user.toDTO
import ar.edu.unsam.proyecto.vetappbackend.repository.notification.NotificationRepository
import ar.edu.unsam.proyecto.vetappbackend.repository.pet.PetRepository
import ar.edu.unsam.proyecto.vetappbackend.repository.user.AuthCredentialsRepository
import ar.edu.unsam.proyecto.vetappbackend.repository.user.LocationInfoRepository
import ar.edu.unsam.proyecto.vetappbackend.repository.user.PetOwnerRepository
import ar.edu.unsam.proyecto.vetappbackend.repository.user.VetRepository
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.transaction.Transactional
import org.hamcrest.Matchers.greaterThan
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDate
import java.time.LocalTime
import java.util.*


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Tag("integration")
class VetControllerIntegrationTest {

    @Autowired
    private lateinit var authCredentialsRepository: AuthCredentialsRepository

    @Autowired
    private lateinit var petOwnerRepository: PetOwnerRepository

    @Autowired
    private lateinit var notificationRepository: NotificationRepository

    @Autowired
    private lateinit var petRepository: PetRepository

    @Autowired
    private lateinit var vetRepository: VetRepository

    @Autowired
    private lateinit var locationInfoRepository: LocationInfoRepository

    @Autowired
    private lateinit var mockMvc: MockMvc //Permite simular peticiones HTTP.

    @Autowired
    lateinit var objectMapper: ObjectMapper //Convierto objetos a JSON.

    @BeforeEach
    fun setup() {
        // Puedo inicializar datos de prueba en la base de datos.
    }

    @Test
    @DisplayName("Debe devolver una lista de veterinarios")
    fun retornar_todosLos_veterionarios() {

        // Arrange
        val url = "/vet/get-all"

        // Act
        val result = mockMvc.perform(get(url))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.length()").isNumber) // permite comprobar que la longitud del JSON de respuesta es un número.Esto se usa para verificar que el endpoint devuelve una lista de elementos
            .andReturn()

        println(result.response.contentAsString)
    }

    @ParameterizedTest(name = "ID pasajero = {0}, nombre esperado = {1}")
    @CsvSource(
        "5, Adrian",
        "6, Lucas"
    )

    @DisplayName("Debe retornar un veterinario cuando el ID es válido")
    fun retornar_un_veterinario_segun_id(id: Int, nombreEsperado: String) {

        // Arrange
        val url = "/vet/get-one-by-id"

        // Act
        val result = mockMvc.perform(
            get(url)
                .param("idVet", id.toString())
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Verifica que la respuesta este en formato JSON.
            .andReturn()

        val json = objectMapper.readTree(result.response.contentAsString) // Convierte la respuesta JSON en un objeto, permitiendo acceder a sus valores.

        println(result.response.contentAsString)

        // Assert
        assertEquals(id.toInt(), json["id"].asInt())
        assertEquals(nombreEsperado, json["name"].asText())
        assertNotNull(json["surname"])
        assertNotNull(json["photo"])
        assertNotNull(json["email"])
        assertNotNull(json["telephone"])
        assertNotNull(json["licence"])
        assertNotNull(json["speciality"])
        assertNotNull(json["businessHours"])
        assertNotNull(json["professionalEmail"])
        assertNotNull(json["professionalTelephone"])
        assertNotNull(json["professionalAddress"])
        assertNotNull(json["professionalLocality"])
        assertNotNull(json["professionalPostalCode"])
    }

    @ParameterizedTest(name = "ID veterinario inexistente = {0}")
    @CsvSource("145")
    @DisplayName("Debe devolver error 404 cuando el veterinario no existe")
    fun retornar_un_veterinario_whenNoExists(id: String){

        val resultado = mockMvc.perform(
            get("/vet/get-one-by-id")
                .param("idVet", id.toString())
        )
            .andExpect(status().isNotFound)
            .andReturn()

        val mensaje = resultado.resolvedException?.message
        assertEquals("No se encontró los datos del usuario: $id", mensaje)

    }

    @ParameterizedTest(name = "ID veterinario = {0}, nombre esperado = {1}")
    @CsvSource("5, Claudio")
    @DisplayName("Debe actualizar el nombre del veterinario correctamente")
    fun actualizar_veterinario_exitoso(idVet: Int, nombreEsperado: String) {

        // Arrange
        val putUrl = "/vet/update"
        val getUrl = "/vet/get-one-by-id"

        val vetExistente = vetRepository.findById(idVet)
            .orElseThrow { IllegalStateException("No se encontró el veterinario con ID $idVet") }

        val vetDTOActualizado = vetExistente.toDTO().copy(
            name = nombreEsperado
        )

        val requestJson = objectMapper.writeValueAsString(vetDTOActualizado)

        // Act
        mockMvc.perform(
            put(putUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        ).andExpect(status().isOk)

        val result = mockMvc.perform(
            get(getUrl)
                .param("idVet", idVet.toString())
        )
            .andExpect(status().isOk)
            .andReturn()

        println("Respuesta del veterinario correctamente actualizado: ${result.response.contentAsString} ")

        // Assert
        val json = objectMapper.readTree(result.response.contentAsString)
        assertEquals(nombreEsperado, json["name"].asText())
    }

    @ParameterizedTest(name = "ID veterinario inexistente = {0}")
    @CsvSource("9999")
    @DisplayName("No debe actualizar si el veterinario no existe")
    fun actualizar_veterinario_inexistente(idVetInexistente: Int) {

        // Arrange
        val putUrl = "/vet/update"

        val vetDTOInexistente = VetDTO(
            id = idVetInexistente,
            dni = 12345678,
            name = "NombreInvalido",
            surname = "Apellido",
            photo = "",
            email = "inexistente@gmail.com",
            telephone = "0000000000",
            licence = "0000",
            speciality = "General",
            businessHours = "9-18",
            professionalEmail = "pro@vet.com",
            professionalAddress = "Falsa 123",
            professionalTelephone = "0000",
            professionalLocality = "Ciudad",
            professionalPostalCode = "0000",
            idInfoLocation = 1,
            address = "Calle",
            country = "Pais",
            province = "Provincia",
            locality = "Localidad",
            postalCode = "0000",
            idAuthCredentials = 1,
            username = "fakeuser",
            password = "fakepass"
        )

        val requestJson = objectMapper.writeValueAsString(vetDTOInexistente)

        // Act & Assert
        val result = mockMvc.perform(
            put(putUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        )
            .andExpect(status().isNotFound)
            .andReturn()

        println("Response Code:${result.response.status}")
    }


    @ParameterizedTest(name = "Veterinario ID = {0}, código esperado = {1}")
    @CsvSource(
        "5,200"
    )
    @Transactional
    @DisplayName("Debe dar 200 al eliminar un veterinario correctamente")
    fun eliminar_veterinario_exitoso(idVet: Int, expect: Int) {

        // Arrange
        val deleteUrl = "/vet/delete"
        val getUrl = "/vet/get-one-by-id"

        val vetExistente = vetRepository.findById(idVet).orElseThrow { IllegalStateException("No se encontró el vet con ID $idVet") }

        val petsVinculadas = petRepository.findAllByVetsContains(vetExistente)
        for (pet in petsVinculadas) {
            pet.vets.remove(vetExistente)
            petRepository.save(pet)
        }

        // Act & Assert
        mockMvc.perform(
            delete(deleteUrl)
                .param("idVet", idVet.toString())
        )
            .andExpect(status().`is`(expect))

        mockMvc.perform(get(getUrl).param("idVet", idVet.toString()))
            .andExpect(status().isNotFound)
    }

    @ParameterizedTest(name = "Veterinario ID = {0}")
    @CsvSource(
        "9999"
    )
    @DisplayName("No debe eliminar un veterinario inexistente y devolver error 404")
    fun eliminar_veterinario_inexistente(idVet: Int) {

        //Arrange
        val deleteUrl = "/vet/delete"

        //Act & Assert
        mockMvc.perform(
            delete(deleteUrl)
                .param("idVet", idVet.toString())
        )
            .andExpect(status().isNotFound)
    }

    @Test
    @DisplayName("Debe devolver todas las mascotas asociadas a un veterinario existente")
    @Transactional
    fun getAllPets_de_veterinario_existente() {
        // Arrange
        val idVet = 5
        val url = "/vet/get-all-pets"

        // Act & Assert
        mockMvc.perform(get(url).param("idVet", idVet.toString()))
            .andDo { result ->

                println("Mascotas devueltas: ${result.response.contentAsString}")
            }
            .andExpect(status().isOk)
            .andExpect(jsonPath("$").isArray)
            .andExpect(jsonPath("$.length()").value(greaterThan(0)))
            .andExpect(jsonPath("$[0].id").exists())
            .andExpect(jsonPath("$[0].name").exists())

    }

    @Test
    @DisplayName("Debe fallar al intentar obtener mascotas de un veterinario inexistente")
    @Transactional
    fun getAllPets_de_veterinario_inexistente() {
        // Arrange
        val idVet = -1
        val url = "/vet/get-all-pets"

        // Act & Assert
        mockMvc.perform(get(url).param("idVet", idVet.toString()))
            .andExpect(status().isNotFound)
            .andDo { result ->
                println("Respuesta al intentar obtener mascotas de un veterinario inexistente: ${result.response.contentAsString}")
            }
    }

    @Test
    @DisplayName("Debe devolver a Oli como mascota  por nombre, vacunas pendientes y turnos")
    @Transactional
    fun getAllByFilterPet_de_veterinario_existente_con_filtros() {
        // Arrange
        val idVet = 5
        val url = "/vet/get-all-pets-by-filter"
        val filter = mapOf(
            "name" to "Ol",
            "hasPendingVaccines" to true,
            "hasMedicalShift" to true
        )

        val jsonBody = objectMapper.writeValueAsString(filter)

        // Act & Assert
        mockMvc.perform(
            post(url)
                .param("idVet", idVet.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody)
        )
            .andDo { result ->
                println("Mascotas filtradas: ${result.response.contentAsString}")
            }
            .andExpect(status().isOk)
            .andExpect(jsonPath("$").isArray)
            .andExpect(jsonPath("$[0].id").exists())
            .andExpect(jsonPath("$[0].name").exists())
    }

    @Test
    @DisplayName("Debe devolver todas las mascotas asociadas a un veterinario cuando se pasa el filtro vacío")
    @Transactional
    fun getAllByFilterPet_de_veterinario_existente_sin_filtros() {
        // Arrange
        val idVet = 5
        val url = "/vet/get-all-pets-by-filter"

        val filtroVacio = "{}"

        // Act & Assert
        mockMvc.perform(
            post(url)
                .param("idVet", idVet.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(filtroVacio)
        )
            .andDo { result ->
                println("Mascotas sin filtro: ${result.response.contentAsString}")
            }
            .andExpect(status().isOk)
            .andExpect(jsonPath("$").isArray)
            .andExpect(jsonPath("$.length()").value(org.hamcrest.Matchers.greaterThan(0)))
            .andExpect(jsonPath("$[0].id").exists())
            .andExpect(jsonPath("$[0].name").exists())
    }

    @Test
    @DisplayName("Debe devolver las mascotas asociadas a un veterinario que NO tienen vacunas pendientes")
    @Transactional
    fun getAllByFilterPet_sin_vacunas_pendientes() {
        // Arrange
        val idVet = 5
        val url = "/vet/get-all-pets-by-filter"

        val filter = mapOf(
            "hasPendingVaccines" to false
        )

        val jsonBody = objectMapper.writeValueAsString(filter)

        // Act & Assert
        mockMvc.perform(
            post(url)
                .param("idVet", idVet.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody)
        )
            .andDo { result ->
                println("Mascotas sin vacunas pendientes: ${result.response.contentAsString}")
            }
            .andExpect(status().isOk)
            .andExpect(jsonPath("$").isArray)
            .andExpect(jsonPath("$.length()").value(org.hamcrest.Matchers.greaterThanOrEqualTo(0)))
    }

    @Test
    @DisplayName("Debe devolver los turnos médicos del día de hoy para un veterinario")
    @Transactional
    fun getAllMedicalShiftFilter_porHoy() {
        // Arrange
        val idVet = 5
        val url = "/vet/get-all-medical-shift-by-filter"
        val filtroHoy = """
        {
            "today": true
        }
    """.trimIndent()

        // Act & Assert
        mockMvc.perform(
            post(url)
                .param("idVet", idVet.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(filtroHoy)
        )
            .andDo { result ->
                println("Turnos de hoy: ${result.response.contentAsString}")
            }
            .andExpect(status().isOk)
            .andExpect(jsonPath("$").isArray)
    }

    @Test
    @DisplayName("Debe fallar al intentar obtener turnos con un filtro today inválido")
    @Transactional
    fun getAllMedicalShiftFilter_debeFallarConTodayInvalido() {
        // Arrange
        val idVet = 5
        val url = "/vet/get-all-medical-shift-by-filter"

        val filtroInvalido = """
    {
        "today": "invalid-value" // Valor incorrecto en lugar de un booleano
    }
    """.trimIndent()

        // Act & Assert
        mockMvc.perform(
            post(url)
                .param("idVet", idVet.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(filtroInvalido)
        )
            .andExpect(status().isBadRequest)
            .andDo { result ->
                println("Respuesta al buscar turnos con filtro today inválido: ${result.response.contentAsString}")
            }
    }

    @Test
    @DisplayName("Debe devolver los turnos médicos de la semana actual para un veterinario")
    @Transactional
    fun getAllMedicalShiftFilter_turnosDeEstaSemana() {
        // Arrange
        val idVet = 5
        val url = "/vet/get-all-medical-shift-by-filter"
        val filtroSemana = """
        {
            "thisWeek": true
        }
    """.trimIndent()

        // Act & Assert
        mockMvc.perform(
            post(url)
                .param("idVet", idVet.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(filtroSemana)
        )
            .andDo { result ->
                println("Turnos de esta semana: ${result.response.contentAsString}")
            }
            .andExpect(status().isOk)
            .andExpect(jsonPath("$").isArray)
            .andExpect(jsonPath("$.length()").value(org.hamcrest.Matchers.greaterThan(0)))
            .andExpect(jsonPath("$[0].id").exists())
            .andExpect(jsonPath("$[0].date").exists())
    }



    @Test
    @DisplayName("Debe fallar al intentar obtener turnos con un filtro thisWeek inválido")
    @Transactional
    fun getAllMedicalShiftFilter_debeFallarConThisWeekInvalido() {
        // Arrange
        val idVet = 5
        val url = "/vet/get-all-medical-shift-by-filter"
        val filtroInvalido = """
    {
        "thisWeek": "invalid-value"
    }
    """.trimIndent()

        // Act & Assert
        mockMvc.perform(
            post(url)
                .param("idVet", idVet.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(filtroInvalido)
        )
            .andExpect(status().isBadRequest)
            .andDo { result ->
                println("Respuesta al buscar turnos con filtro thisWeek inválido: ${result.response.contentAsString}")
            }
    }

    @Test
    @DisplayName("Debe devolver los turnos médicos de una fecha específica para un veterinario")
    @Transactional
    fun getAllMedicalShiftFilter_turnosPorFechaEspecifica() {
        // Arrange
        val idVet = 5
        val url = "/vet/get-all-medical-shift-by-filter"
        val fechaEspecifica = "2025-05-27"

        val filtroFecha = """
        {
            "day": "$fechaEspecifica"
        }
    """.trimIndent()

        // Act & Assert
        mockMvc.perform(
            post(url)
                .param("idVet", idVet.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(filtroFecha)
        )
            .andDo { result ->
                println("Turnos en la fecha $fechaEspecifica: ${result.response.contentAsString}")
            }
            .andExpect(status().isOk)
            .andExpect(jsonPath("$").isArray)
            .andExpect(jsonPath("$.length()").value(org.hamcrest.Matchers.greaterThanOrEqualTo(0)))
            .andExpect(jsonPath("$[0].date").exists())
            .andExpect(jsonPath("$[0].date").value(fechaEspecifica))
    }


    @Test
    @DisplayName("Debe devolver todas las notificaciones asociadas a un veterinario existente")
    @Transactional
    fun getAllNotifications_de_veterinario_existente() {
        // Arrange
        val idVet = 5
        val idOwner = 2
        val url = "/vet/get-all-notifications"

        val vet = vetRepository.findById(idVet).orElseThrow { RuntimeException("Vet no encontrado con ID $idVet") }
        val owner = petOwnerRepository.findById(idOwner).orElseThrow { RuntimeException("PetOwner no encontrado con ID $idOwner") }

        val pet = petRepository.findAllPetsByOwnerId(idOwner).firstOrNull()
            ?: petRepository.findAll().firstOrNull()
            ?: throw RuntimeException("No hay mascotas en la base")

        val notification = Notification(
            pet = pet,
            vet = vet,
            petOwner = owner,
            date = LocalDate.now(),
            hour = LocalTime.now(),
            notificationDate = LocalDate.now(),
            type = TypeOfNotification.SHIFT_REMINDER,
            subject = "Recordatorio Test",
            message = "Mensaje de prueba"
        )
        notificationRepository.save(notification)

        // Act & Assert
        mockMvc.perform(get(url).param("idVet", idVet.toString()))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$").isArray)
            .andExpect(jsonPath("$[0].id").exists())
            .andExpect(jsonPath("$[0].namePet").exists())
            .andExpect(jsonPath("$[0].nameVet").exists())
            .andExpect(jsonPath("$[0].namePetOwner").exists())
            .andExpect(jsonPath("$[0].date").exists())
            .andExpect(jsonPath("$[0].hour").exists())
            .andExpect(jsonPath("$[0].notificationDate").exists())
            .andExpect(jsonPath("$[0].type").exists())
            .andExpect(jsonPath("$[0].subject").exists())
            .andExpect(jsonPath("$[0].message").exists())
            .andDo { result ->
                println("Notificaciones recibidas: ${result.response.contentAsString}")
            }
    }


}