package ar.edu.unsam.proyecto.vetappbackend.pet

import ar.edu.unsam.proyecto.vetappbackend.dto.pet.PreExistenceDiseaseDTO
import ar.edu.unsam.proyecto.vetappbackend.dto.pet.VaccineDTO
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime


@AutoConfigureMockMvc
@ActiveProfiles("test")
@Tag("integration")
@SpringBootTest
class VaccineControllerIntegrationTest {

    @Autowired private lateinit var mockMvc: MockMvc

    @Autowired lateinit var objectMapper: ObjectMapper

    @Test
    @DisplayName("Debe obtener todos las vacunas correctamente")
    fun getAllVaccine() {
        val url = "/vaccines/get-all"
        val result = mockMvc.perform(get(url))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isArray)
            .andExpect(jsonPath("$.length()").isNotEmpty)
            .andReturn()
        val content = result.response.contentAsString
        println("Todos las vacunas para el test:\n$content")
    }


    @DisplayName("Debe retornar una vacuna por su ID")
    @ParameterizedTest(name = "idVaccine = {0}")
    @CsvSource("1")
    @Transactional
    fun getOneVaccineByIdExistent(idVaccine: Int) {
        val url = "/vaccines/get-one-by-id"
        val result = mockMvc.perform(get(url).param("idVaccine", idVaccine.toString())).andExpect(status().isOk).andReturn()
        val content = result.response.contentAsString
        println("Vacuna con el ID $idVaccine:\n$content")
    }


    @ParameterizedTest(name = "idVaccine = {0}")
    @CsvSource("-1, 0, 99")
    @DisplayName("Debe lanzar una exepcion cuando el ID de una vacuna es incorrecto")
    @Transactional
    fun getOneVaccineByIdNonExistent(idVaccine: Int) {
        val url = "/vaccines/get-one-by-id"
        val result = mockMvc.perform(get(url)
            .param("idVaccine", idVaccine.toString()))
            .andExpect(status().isNotFound)
            .andReturn()
        val content = result.response.contentAsString
        println("Error al buscar una vacuna con ID $idVaccine:\n$content")
    }


    @DisplayName("Debe crear una nueva vacuna correctamente")
    @ParameterizedTest(name = "medicalHistoryId = {0}")
    @CsvSource("1")
    fun createVaccine(idMedicalHistory: Int) {
        // Arrange
        val newVaccine = VaccineDTO(
            id = 0,
            medicalHistoryId = idMedicalHistory,
            applicationDate = "2025-05-30",
            expirationDate = "2026-05-30",
            description = "Se aplico correctamente",
            batchNumber = 112456,
            completed = false,
            type = "DISTEMPER"
        )
        val url = "/vaccines/create"
        val newVaccineJson = objectMapper.writeValueAsString(newVaccine)
        // Act & Assert
        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(newVaccineJson)).andExpect(status().isOk)
    }


    @DisplayName("No puede crear una nueva vacuna correctamente, historial medico inexistente")
    @ParameterizedTest(name = "medicalHistoryId = {0}")
    @CsvSource("-1, 0, 99")
    fun createVaccineNonExistent(idMedicalHistory: Int) {
        // Arrange
        val newVaccine = VaccineDTO(
            id = 0,
            medicalHistoryId = idMedicalHistory,
            applicationDate = "2025-05-30",
            expirationDate = "2026-05-30",
            description = "Se aplico correctamente",
            batchNumber = 112456,
            completed = false,
            type = "DISTEMPER"
        )
        val url = "/vaccines/create"
        val vaccineJson = objectMapper.writeValueAsString(newVaccine)
        // Act & Assert
        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(vaccineJson)).andExpect(status().isNotFound)
    }

    @DisplayName("Debe actualizar una vacuna correctamente")
    @ParameterizedTest(name = "idVaccine = {0}, newType = {1}")
    @CsvSource("1, DEWORMING")
    fun updateVaccine(idVaccine: Int, newType: String) {
        // Arrange
        val url = "/vaccines/get-one-by-id"
        val resultBefore = mockMvc.perform(
            get(url)
                .param("idVaccine", idVaccine.toString())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk).andReturn()
        val contentBefore = resultBefore.response.contentAsString
        val jsonBefore = objectMapper.readTree(contentBefore)
        println("Respuesta ANTERIOR: $contentBefore")

        // Arrange
        val vaccineDTO = VaccineDTO(
            id = jsonBefore["id"].asInt(),
            medicalHistoryId = jsonBefore["medicalHistoryId"].asInt(),
            applicationDate = jsonBefore["applicationDate"].asText(),
            expirationDate = jsonBefore["expirationDate"].asText(),
            description = jsonBefore["description"].asText(),
            batchNumber = jsonBefore["batchNumber"].asInt(),
            completed = jsonBefore["completed"].asBoolean(),
            type = newType
        )
        val updatedJson = objectMapper.writeValueAsString(vaccineDTO)

        // Act
        mockMvc.perform(
            put("/vaccines/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedJson)
        ).andExpect(status().isOk)

        // Act
        val resultAfter = mockMvc.perform(
            get(url)
                .param("idVaccine", idVaccine.toString())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk).andReturn()
        val contentAfter = resultAfter.response.contentAsString
        val jsonAfter = objectMapper.readTree(contentAfter)
        println("Respuesta ACTUALIZADA: $contentAfter")

        // Assert
        assertEquals(newType, jsonAfter["type"].asText())
    }

}