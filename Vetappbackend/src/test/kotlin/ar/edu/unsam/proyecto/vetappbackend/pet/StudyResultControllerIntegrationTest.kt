package ar.edu.unsam.proyecto.vetappbackend.pet

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.MedicalHistory
import ar.edu.unsam.proyecto.vetappbackend.dto.pet.StudyResultDTO
import ar.edu.unsam.proyecto.vetappbackend.repository.pet.MedicalHistoryRepository
import ar.edu.unsam.proyecto.vetappbackend.repository.pet.StudyResultRepository
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.transaction.Transactional
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Tag
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import java.time.LocalDate
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.http.MediaType
import kotlin.test.assertTrue

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Tag("integration")
class StudyResultControllerIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc //Permite simular peticiones HTTP.

    @Autowired
    private lateinit var studyResultRepository: StudyResultRepository

    @Autowired
    lateinit var objectMapper: ObjectMapper //Convierto objetos a JSON.

    @DisplayName("Debe obtener todos los StudyResults correctamente")
    @Test
    @Transactional
    fun getAllStudyResults_debeRetornarListaDeStudyResults() {
        // Act & Assert
        val result = mockMvc.perform(get("/study-result/get-all"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isArray)
            .andExpect(jsonPath("$.length()").isNotEmpty)
            .andReturn()

        val content = result.response.contentAsString
        println("Todos los StudyResults:\n$content")
    }

    @ParameterizedTest(name = "idStudyResult = {0}")
    @CsvSource("1")
    @DisplayName("Debe retornar un StudyResult por ID")
    @Transactional
    fun getOneStudyResultById_debeRetornarStudyResultPorId(idStudyResult: Int) {
        val result = mockMvc.perform(get("/study-result/get-one-by-id")
            .param("idStudyResult", idStudyResult.toString()))
            .andExpect(status().isOk)
            .andReturn()

        val content = result.response.contentAsString
        println("StudyResult con ID $idStudyResult:\n$content")
    }

    @ParameterizedTest(name = "idStudyResult = {0}")
    @CsvSource("99999")
    @DisplayName("Debe retornar error cuando el ID del studyResult es incorrecto")
    @Transactional
    fun getOneStudyResultById_debeRetornarErrorCuandoIdEsIncorrecto(idStudyResult: Int) {

        //Arrange
        val url = "/study-result/get-one-by-id"

        //Act and Assert
        val result = mockMvc.perform(get(url)
            .param("idStudyResult", idStudyResult.toString()))
            .andExpect(status().isNotFound)
            .andReturn()

        val content = result.response.contentAsString
        println("Error al buscar StudyResult con ID $idStudyResult:\n$content")
    }



    @DisplayName("Debe crear un nuevo StudyResult correctamente")
    @Transactional
    @ParameterizedTest(name = "medicalHistoryId = {0}")
    @CsvSource("1")
    fun createStudyResult_debeCrearNuevoStudyResult(medicalHistoryId: Int) {
        // Arrange
        val newStudyResult = StudyResultDTO(
            id = 0,
            medicalHistoryId = medicalHistoryId,
            date = LocalDate.now().toString(),
            fileUrl = "https://archivo.com/study.pdf",
            description = "Análisis general",
            type = "PHARMACOLOGICAL"
        )

        val studyResultJson = objectMapper.writeValueAsString(newStudyResult)

        // Act & Assert
        mockMvc.perform(
            post("/study-result/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(studyResultJson)
        )
            .andExpect(status().isOk)
    }

    @ParameterizedTest(name = "medicalHistoryId = {0}")
    @CsvSource("-1")
    @DisplayName("Debe fallar cuando el medicalHistoryId es incorrecto")
    @Transactional
    fun createStudyResult_debeFallarCuandoMedicalHistoryIdEsIncorrecto(medicalHistoryId: String) {
        // Arrange
        val newStudyResult = StudyResultDTO(
            id = 0,
            medicalHistoryId = medicalHistoryId.toIntOrNull() ?: 0,
            date = LocalDate.now().toString(),
            fileUrl = "https://archivo.com/study.pdf",
            description = "Análisis general",
            type = "PHARMACOLOGICAL"
        )

        val studyResultJson = objectMapper.writeValueAsString(newStudyResult)

        // Act & Assert
        mockMvc.perform(
            post("/study-result/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(studyResultJson)
        )
            .andExpect(status().isNotFound)
    }

    @DisplayName("Debe actualizar un StudyResult existente correctamente")
    @Transactional
    @ParameterizedTest(name = "studyResultId = {0}")
    @CsvSource("1")
    fun updateStudyResult_debeActualizarStudyResultExistente(studyResultId: Int) {
        // Arrange
        val existingStudyResult = studyResultRepository.findById(studyResultId)
            .orElseThrow { RuntimeException("StudyResult no encontrado") }

        val updatedDTO = StudyResultDTO(
            id = existingStudyResult.id!!,
            medicalHistoryId = existingStudyResult.medicalHistory!!.id,
            date = existingStudyResult.date.toString(),
            fileUrl = "https://archivo.com/actualizado.pdf",
            description = "Resultado actualizado",
            type = existingStudyResult.type!!.name
        )

        val updatedJson = objectMapper.writeValueAsString(updatedDTO)

        // Act & Assert
        mockMvc.perform(
            put("/study-result/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedJson)
        )
            .andExpect(status().isOk)
    }

    @ParameterizedTest(name = "medicalHistoryId = {0}")
    @CsvSource("-1")
    @DisplayName("Debe fallar al intentar actualizar un StudyResult con medicalHistoryId incorrecto")
    @Transactional
    fun updateStudyResult_debeFallarCuandoMedicalHistoryIdEsIncorrecto(medicalHistoryId: String) {
        // Arrange
        val existingStudyResult = studyResultRepository.findById(1)
            .orElseThrow { RuntimeException("StudyResult no encontrado") }

        val updatedDTO = StudyResultDTO(
            id = existingStudyResult.id!!,
            medicalHistoryId = medicalHistoryId.toIntOrNull() ?: 0,
            date = existingStudyResult.date.toString(),
            fileUrl = "https://archivo.com/actualizado.pdf",
            description = "Resultado actualizado",
            type = existingStudyResult.type!!.name
        )

        val updatedJson = objectMapper.writeValueAsString(updatedDTO)

        // Act & Assert
        mockMvc.perform(
            put("/study-result/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedJson)
        )
            .andExpect(status().isNotFound)
    }

    @DisplayName("Debe eliminar un StudyResult existente correctamente")
    @Transactional
    @ParameterizedTest(name = "studyResultId = {0}")
    @CsvSource("1")
    fun deleteStudyResult_debeEliminarStudyResultExistente(studyResultId: Int) {

        //Arrange
        val studyResult = studyResultRepository.findById(studyResultId)
            .orElseThrow { RuntimeException("StudyResult no encontrado") }

        // Act & Assert
        mockMvc.perform(
            delete("/study-result/delete")
                .param("idStudyResult", studyResultId.toString())
        )
            .andExpect(status().isOk)

        // Verificar que fue eliminado
        val wasDeleted = studyResultRepository.findById(studyResultId).isEmpty
        assertTrue(wasDeleted, "El StudyResult debería haber sido eliminado")
    }

    @ParameterizedTest(name = "studyResultId = {0}")
    @CsvSource("-1")
    @DisplayName("Debe fallar al intentar eliminar un StudyResult inexistente")
    @Transactional
    fun deleteStudyResult_debeFallarCuandoStudyResultNoExiste(studyResultId: Int) {
        //Arrange
        val url = "/study-result/delete"

        // Act & Assert
        mockMvc.perform(
            delete(url)
                .param("idStudyResult", studyResultId.toString())
        )
            .andExpect(status().isNotFound)
    }



}