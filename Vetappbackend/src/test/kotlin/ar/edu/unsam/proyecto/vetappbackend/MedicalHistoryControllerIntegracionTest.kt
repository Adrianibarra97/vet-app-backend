package ar.edu.unsam.proyecto.vetappbackend

import ar.edu.unsam.proyecto.vetappbackend.service.pet.MedicalHistoryService
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.transaction.Transactional
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
@Transactional

class MedicalHistoryControllerIntegracionTest {

    @Autowired
    private lateinit var medicalHistoryService: MedicalHistoryService

    @Autowired
    lateinit var mockMvc: MockMvc


    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    @DisplayName("Se obtienen correctamente todos los medical history")
    fun medicalHistory_shouldReturnMedicalHistory() {
        // Arrange
        val url = "/medical-history/get-all"

        // Act
        mockMvc.perform(
            get(url)
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn()

    }

    @ParameterizedTest(name = "idMedicalHistory = {0}")
    @CsvSource(
        "1",
        "3"
    )
    @DisplayName("Devuelve correctamente un medicalHistory con id válido")
    fun medicalHistory_shouldReturnMedicalHistory_whenIdValido(idMedicalHistory: Int) {
        // Arrange
        val url = "/medical-history/get-one-by-id"

        // Act
        mockMvc.perform(
            get(url)
                .param("idMedicalHistory", idMedicalHistory.toString())
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn()

    }

    @ParameterizedTest(name = "idMedicalHistory = {0}")
    @CsvSource(
        "0",
        "24"
    )
    @DisplayName("Devuelve 404 cuando se pide un medicalHistory con id inválido")
    fun medicalHistory_shouldReturn404_whenIdNoValido(idMedicalHistory: Int) {
        // Arrange
        val url = "/medical-history/get-one-by-id"

        // Act
        mockMvc.perform(
            get(url)
                .param("idMedicalHistory", idMedicalHistory.toString())
        )
            .andExpect(status().isNotFound)

    }

    @ParameterizedTest(name = "id = {0}, summary = {1}, createdAt = {2}, updatedAt = {3}")
    @CsvSource( "15, 'Todo bien', '2025-05-22', '2025-06-01'" )
    @DisplayName("Debe crear correctamente el medicalHistory cuando los datos son válidos")
    fun medicalHistory_shouldCrearMedicalHistory_whenCamposValidos(
        id: Int,
        summary: String,
        createdAt: String,
        updatedAt: String,
    ) {

        // Arrange
        val url = "/medical-history/create"

        val datos = mapOf(
            "id" to id,
            "summary" to summary,
            "createdAt" to createdAt,
            "updatedAt" to updatedAt
        )

        val requestBody = objectMapper.writeValueAsString(datos)


        // Act
        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().isOk)

    }


    @ParameterizedTest(name = "id = {0}, summary = {1}, createdAt = {2}, updatedAt = {3}")
    @CsvSource("11, 'More se encuentra muy muy bien', '2025-01-10' ,'2025-06-02'" )
    @DisplayName("MedicalHistory debe actualizar medicalHistory correctamente")
    fun medicalHistory_shouldUpdateMedicalHistory_whenCamposCorrectos(id: Int, summary: String, createdAt: String, updatedAt: String ) {


        val putUrl = "/medical-history/update"

        val medicalHistoryActualizado = mapOf(
            "id" to id,
            "summary" to summary,
            "createdAt" to createdAt,
            "updatedAt" to updatedAt
        )

        val requestBody = objectMapper.writeValueAsString(medicalHistoryActualizado)

        mockMvc.perform(put(putUrl).contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().isOk).andReturn()

    }

    @ParameterizedTest(name = "id = {0}, summary = {1}, createdAt = {2}, updatedAt = {3}")
    @CsvSource("110, 'More se encuentra muy bien', '2025-01-10' ,'2025-06-02'" )
    @DisplayName("MedicalHistory no debe actualizar medicalHistory con id incorrecto")
    fun medicalHistory_shouldNotUpdateMedicalHistory_whenisIncorrecto(id: Int, summary: String, createdAt: String, updatedAt: String ) {


        val putUrl = "/medical-history/update"

        val medicalHistoryActualizado = mapOf(
            "id" to id,
            "summary" to summary,
            "createdAt" to createdAt,
            "updatedAt" to updatedAt
        )

        val requestBody = objectMapper.writeValueAsString(medicalHistoryActualizado)

        mockMvc.perform(put(putUrl).contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().isNotFound)

    }


    @ParameterizedTest(name = "id= {0}")
    @CsvSource("1, 6")
    @DisplayName("Debe eliminar correctamente medicalHistory si existe")
    fun medicalHistory_shouldReturn200_whenEliminaMedicalHistoryCorrectamnete(idMedicalHistory: Int) {

        //

        mockMvc.perform(
            delete("/medical-history/delete")
                .param("idMedicalHistory", idMedicalHistory.toString())
        )
            .andExpect(status().isOk)
    }

    @ParameterizedTest(name = "id= {0}")
    @CsvSource("0, 20")
    @DisplayName("No puede eliminar medicalHistory con id incorrecto")
    fun medicalHistory_shouldReturn404_whenNoEliminaMedicalHistoryIdIncorrecto(idMedicalHistory: Int) {

        //

        mockMvc.perform(
            delete("/medical-history/delete")
                .param("idMedicalHistory", idMedicalHistory.toString())
        )
            .andExpect(status().isNotFound)
    }

    @ParameterizedTest(name = "id= {0}")
    @CsvSource("1, 5")
    @DisplayName("Se obtienen correctamente todos los recipes de un medical history")
    fun medicalHistory_shouldReturnRecipes(idMedicalHistory: Int) {
        // Arrange
        val url = "/medical-history/get-all-pet-recipes"

        // Act
        mockMvc.perform(
            get(url)
                .param("idMedicalHistory", idMedicalHistory.toString())

        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn()

    }

    @ParameterizedTest(name = "id= {0}")
    @CsvSource("0, 25")
    @DisplayName("No se obtienen todos los recipes de un medical history, id incorrecto")
    fun medicalHistory_shouldNotReturnRecipes(idMedicalHistory: Int) {
        // Arrange
        val url = "/medical-history/get-all-pet-recipes"

        // Act
        mockMvc.perform(
            get(url)
                .param("idMedicalHistory", idMedicalHistory.toString())

        )
            .andExpect(status().isNotFound)

    }

    @ParameterizedTest(name = "id= {0}")
    @CsvSource("1, 5")
    @DisplayName("Se obtienen correctamente todas las preExistingDiseases de un medical history")
    fun medicalHistory_shouldReturnpreExistingDiseases(idMedicalHistory: Int) {
        // Arrange
        val url = "/medical-history/get-all-pet-pre-existence-disease"

        // Act
        mockMvc.perform(
            get(url)
                .param("idMedicalHistory", idMedicalHistory.toString())

        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn()

    }

    @ParameterizedTest(name = "id= {0}")
    @CsvSource("0, 25")
    @DisplayName("No se obtienen todos las preExistingDiseases de un medical history, id incorrecto")
    fun medicalHistory_shouldNotReturnPreExistingDiseases(idMedicalHistory: Int) {
        // Arrange
        val url = "/medical-history/get-all-pet-pre-existence-disease"

        // Act
        mockMvc.perform(
            get(url)
                .param("idMedicalHistory", idMedicalHistory.toString())

        )
            .andExpect(status().isNotFound)

    }

    @ParameterizedTest(name = "id= {0}")
    @CsvSource("1, 5")
    @DisplayName("Se obtienen correctamente todos los studyResults de un medical history")
    fun medicalHistory_shouldReturnstudyResults(idMedicalHistory: Int) {
        // Arrange
        val url = "/medical-history/get-all-pet-study-result"

        // Act
        mockMvc.perform(
            get(url)
                .param("idMedicalHistory", idMedicalHistory.toString())

        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn()

    }

    @ParameterizedTest(name = "id= {0}")
    @CsvSource("0, 25")
    @DisplayName("No se obtienen todos los studyResults de un medical history, id incorrecto")
    fun medicalHistory_shouldNotReturnstudyResults(idMedicalHistory: Int) {
        // Arrange
        val url = "/medical-history/get-all-pet-study-result"

        // Act
        mockMvc.perform(
            get(url)
                .param("idMedicalHistory", idMedicalHistory.toString())

        )
            .andExpect(status().isNotFound)

    }


}