package ar.edu.unsam.proyecto.vetappbackend

import ar.edu.unsam.proyecto.vetappbackend.service.user.MedicalShiftService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.*
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

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Tag("integracion")

class MedicalShiftControllerIntegracionTest {

    @Autowired
    private lateinit var medicalShiftService: MedicalShiftService

    @Autowired
    lateinit var mockMvc: MockMvc


    @Autowired
    lateinit var objectMapper: ObjectMapper

    @BeforeEach
    fun setup() {
    }

//    @ParameterizedTest(name = "parametro1 = {0}, parametro2 = {1}")
//    @CsvSource(
//        "valor1, valor1.1",
//        "valor2, valor2.2"
//    )
    //   @DisplayName("Nombre de lo que debe hacer el tests")
//    fun algo_shouldReturnLoQueDevuelve_whenCondcion(parametro: Tipo) {
//        // Arrange
//        val url = "/pet/medical-history/get-all"
//
//        // Act
//        val result = mockMvc.perform(
//            get(url)
//        )
//            .andExpect(status().isOk)
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andReturn()
//
//    }

    @Test
    @DisplayName("Se obtienen correctamente todos los medical shifts")
    fun medicalShift_shouldReturnMedicalShifts() {
        // Arrange
        val url = "/medical-shift/get-all"

        // Act
        mockMvc.perform(
            get(url)
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn()

    }

    @ParameterizedTest(name = "idMedicalShift = {0}")
    @CsvSource(
        "1",
        "3"
    )
    @DisplayName("Devuelve correctamente un medicalShift con id válido")
    fun medicalShift_shouldReturnMedicalShift_whenIdValido(idMedicalShift: Int) {
        // Arrange
        val url = "/medical-shift/get-one-by-id"

        // Act
        mockMvc.perform(
            get(url)
                .param("idMedicalShift", idMedicalShift.toString())
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn()

    }

    @ParameterizedTest(name = "idMedicalShift = {0}")
    @CsvSource(
        "0",
        "24"
    )
    @DisplayName("Devuelve 404 cuando se pide un medicalShift con id inválido")
    fun medicalShift_shouldReturn404_whenIdNoValido(idMedicalShift: Int) {
        // Arrange
        val url = "/medical-shift/get-one-by-id"

        // Act
        mockMvc.perform(
            get(url)
                .param("idMedicalShift", idMedicalShift.toString())
        )
            .andExpect(status().isNotFound)

    }

    @ParameterizedTest(name = "id = {0}, date = {1}, hour = {2}, vetId = {3}, petId = {4}")
    @CsvSource( "7, '2025-09-29', '11:00', 6,  1" )
    @DisplayName("Debe crear correctamente el medicalShift cuando los datos son válidos")
    fun medicalShift_shouldCrearMedicalShift_whenCamposValidos(
        id: Int,
        date: String,
        hour: String,
        vetId: Int,
        petId: Int
    ) {

        // Arrange
        val url = "/medical-shift/create"

        val datos = mapOf(
            "id" to id,
            "date" to date,
            "hour" to hour,
            "vetId" to vetId,
            "petId" to petId
        )

        val requestBody = objectMapper.writeValueAsString(datos)


        // Act
        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().isOk)

    }

    @ParameterizedTest(name = "id = {0}, date = {1}, hour = {2}, vetId = {3}, petId = {4}")
    @CsvSource("6, '2025-06-02' ,'12:00', 6, 10" )
    @DisplayName("MedicalShift debe actualizar turno correctamente")
    fun medicalShift_shouldUpdateMedicalShift_whenCamposCorrectos(id: Int, date: String, hour: String, vetId: Int, petId: Int) {

        val putUrl = "/medical-shift/update"

        val turnoActualizado = mapOf(
            "id" to id,
            "date" to date,
            "hour" to hour,
            "vetId" to vetId,
            "petId" to petId,
            )

        val requestBody = objectMapper.writeValueAsString(turnoActualizado)

        mockMvc.perform(put(putUrl).contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().isOk).andReturn()

    }

    @ParameterizedTest(name = "id = {0}, date = {1}, hour = {2}, vetId = {3}, petId = {4}")
    @CsvSource( "7, '2025-09-29', '11:00', 2,  1" )
    @DisplayName("Debe fallar la creacion del medicaShift cuando id de veterinario es incorrecto")
    fun medicalShift_shouldNoCrearMedicalShift_whenIdVeterinarioIncorrecto(
        id: Int,
        date: String,
        hour: String,
        vetId: Int,
        petId: Int
    ) {

        // Arrange
        val url = "/medical-shift/create"

        val datos = mapOf(
            "id" to id,
            "date" to date,
            "hour" to hour,
            "vetId" to vetId,
            "petId" to petId
        )

        val requestBody = objectMapper.writeValueAsString(datos)


        // Act
        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().isNotFound)

    }

    @ParameterizedTest(name = "id = {0}, date = {1}, hour = {2}, vetId = {3}, petId = {4}")
    @CsvSource( "7, '2025-09-29', '11:00', 6,  25" )
    @DisplayName("Debe fallar la creacion del medicaShift cuando id de mascota es incorrecto")
    fun medicalShift_shouldNoCrearMedicalShift_whenIdMascotaIncorrecto(
        id: Int,
        date: String,
        hour: String,
        vetId: Int,
        petId: Int
    ) {

        // Arrange
        val url = "/medical-shift/create"

        val datos = mapOf(
            "id" to id,
            "date" to date,
            "hour" to hour,
            "vetId" to vetId,
            "petId" to petId
        )

        val requestBody = objectMapper.writeValueAsString(datos)


        // Act
        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().isNotFound)

    }

    @ParameterizedTest(name = "id = {0}, date = {1}, hour = {2}, vetId = {3}, petId = {4}")
    @CsvSource("6, '2025-06-02' ,'12:00', 2, 10" )
    @DisplayName("MedicalShift no debe actualizar turno correctamente si id de veterinario es incorrecto")
    fun medicalShift_shouldNotUpdateMedicalShift_whenVeterinarioIncorrecto(id: Int, date: String, hour: String, vetId: Int, petId: Int) {

        val putUrl = "/medical-shift/update"

        val turnoActualizado = mapOf(
            "id" to id,
            "date" to date,
            "hour" to hour,
            "vetId" to vetId,
            "petId" to petId,
        )

        val requestBody = objectMapper.writeValueAsString(turnoActualizado)

        mockMvc.perform(put(putUrl).contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().isNotFound)

    }

    @ParameterizedTest(name = "id = {0}, date = {1}, hour = {2}, vetId = {3}, petId = {4}")
    @CsvSource("6, '2025-06-02' ,'12:00', 6, 25" )
    @DisplayName("MedicalShift no debe actualizar turno correctamente si id de mascota es incorrecto")
    fun medicalShift_shouldNotUpdateMedicalShift_whenMascotaIncorrecta(id: Int, date: String, hour: String, vetId: Int, petId: Int) {

        val putUrl = "/medical-shift/update"

        val turnoActualizado = mapOf(
            "id" to id,
            "date" to date,
            "hour" to hour,
            "vetId" to vetId,
            "petId" to petId,
        )

        val requestBody = objectMapper.writeValueAsString(turnoActualizado)

        mockMvc.perform(put(putUrl).contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().isNotFound)

    }

//    @ParameterizedTest(name = "id = {0}, date = {1}, hour = {2}, vetId = {3}, petId = {4}")
//    @CsvSource("6, '2025-06-35' ,'12:00', 6, 10" )
//    @DisplayName("MedicalShift no debe actualizar turno correctamente si fecha es incorrecta")
//    fun medicalShiftService_shouldNotUpdateMedicalShift_whenFechaIncorrecta(id: Int, date: String, hour: String, vetId: Int, petId: Int) {
//
//        val putUrl = "/medical-shift/update"
//
//        val turnoActualizado = mapOf(
//            "id" to id,
//            "date" to date,
//            "hour" to hour,
//            "vetId" to vetId,
//            "petId" to petId,
//        )
//
//        val requestBody = objectMapper.writeValueAsString(turnoActualizado)
//
//        mockMvc.perform(put(putUrl).contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().isInternalServerError)
//
//    }

    @ParameterizedTest(name = "id= {0}")
    @CsvSource("1, 6")
    @DisplayName("Debe eliminar correctamente medicalShift si existe")
    fun medicalShift_shouldReturn200_whenEliminaMedicalShiftCorrectamnete(idMedicalShift: Int) {

        //

        mockMvc.perform(
            delete("/medical-shift/delete")
                .param("idMedicalShift", idMedicalShift.toString())
        )
            .andExpect(status().isOk)
    }

    @ParameterizedTest(name = "id= {0}")
    @CsvSource("7, 10")
    @DisplayName("No puede eliminar medicalShift con id incorrecto")
    fun medicalShift_shouldReturn404_whenNoEliminaMedicalShiftIdIncorrecto(idMedicalShift: Int) {

        //

        mockMvc.perform(
            delete("/medical-shift/delete")
                .param("idMedicalShift", idMedicalShift.toString())
        )
            .andExpect(status().isNotFound)
    }


}