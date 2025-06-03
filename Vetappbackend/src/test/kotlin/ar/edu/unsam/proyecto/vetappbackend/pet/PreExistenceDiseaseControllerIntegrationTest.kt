package ar.edu.unsam.proyecto.vetappbackend.pet

import ar.edu.unsam.proyecto.vetappbackend.dto.pet.PreExistenceDiseaseDTO
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import kotlin.Int

@AutoConfigureMockMvc
@ActiveProfiles("test")
@Tag("integration")
@SpringBootTest
open class PreExistenceDiseaseControllerIntegrationTest {

    @Autowired private lateinit var mockMvc: MockMvc

    @Autowired lateinit var objectMapper: ObjectMapper

    @Test
    @DisplayName("Debe obtener todos las enfermedades preexistentes correctamente")
    open fun getAllPreExistenceDisease() {
        val url = "/pre-existence-disease/get-all"
        val result = mockMvc.perform(get(url))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isArray)
            .andExpect(jsonPath("$.length()").isNotEmpty)
            .andReturn()
        val content = result.response.contentAsString
        println("Todos las enfermedades preexistentes para el test:\n$content")
    }


    @DisplayName("Debe retornar una enfermedad pre-existente por el ID")
    @ParameterizedTest(name = "idPreExistenceDisease = {0}")
    @CsvSource("1")
    @Transactional
    open fun getOnePreExistenceDiseaseByIdExistent(idPreExistenceDisease: Int) {
        val url = "/pre-existence-disease/get-one-by-id"
        val result = mockMvc.perform(get(url).param("idPreExistenceDisease", idPreExistenceDisease.toString())).andExpect(status().isOk).andReturn()
        val content = result.response.contentAsString
        println("Enfermedad pre-existente con ID $idPreExistenceDisease:\n$content")
    }


    @ParameterizedTest(name = "idPreExistenceDisease = {0}")
    @CsvSource("-1, 0, 99")
    @DisplayName("Debe retornar error cuando el ID de una enfermedad pre-existente es incorrecto")
    @Transactional
    open fun getOnePreExistenceDiseaseByIdNonExistent(idPreExistenceDisease: Int) {
        val url = "/pre-existence-disease/get-one-by-id"
        val result = mockMvc.perform(get(url)
            .param("idPreExistenceDisease", idPreExistenceDisease.toString()))
            .andExpect(status().isNotFound)
            .andReturn()
        val content = result.response.contentAsString
        println("Error al buscar una enfermeda pre-existente con ID $idPreExistenceDisease:\n$content")
    }


    @DisplayName("Debe crear una nueva enfermedad pre-existente correctamente")
    @ParameterizedTest(name = "medicalHistoryId = {0}")
    @CsvSource("4")
    open fun createPreExistenceDisease(idMedicalHistory: Int) {
        // Arrange
        val newPreExistenceDisease = PreExistenceDiseaseDTO(
            id = 0,
            medicalHistoryId = idMedicalHistory,
            isActive = false,
            observation = "Esta estable",
            diagnosisDate = "2025-06-24",
            severity = "Stable",
            type = "DIABETES"
        )
        val url = "/pre-existence-disease/create"
        val preExistenceDiseaseJson = objectMapper.writeValueAsString(newPreExistenceDisease)
        // Act & Assert
        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(preExistenceDiseaseJson)).andExpect(status().isOk)
    }


    @DisplayName("No puede crear una nueva enfermedad pre-existente correctamente, historial medico inexistente")
    @ParameterizedTest(name = "medicalHistoryId = {0}")
    @CsvSource("-1, 0, 99")
    open fun createPreExistenceDiseaseNonExistent(idMedicalHistory: Int) {
        // Arrange
        val newPreExistenceDisease = PreExistenceDiseaseDTO(
            id = 0,
            medicalHistoryId = idMedicalHistory,
            isActive = false,
            observation = "Esta estable",
            diagnosisDate = "2025-06-24",
            severity = "Stable",
            type = "DIABETES"
        )
        val url = "/pre-existence-disease/create"
        val preExistenceDiseaseJson = objectMapper.writeValueAsString(newPreExistenceDisease)
        // Act & Assert
        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(preExistenceDiseaseJson)).andExpect(status().isNotFound)
    }


    @DisplayName("Debe actualizar una enfermedad pre-existente correctamente")
    @ParameterizedTest(name = "idPreExistenceDisease = {0}, newSeverity = {1}, newType = {2}")
    @CsvSource("1, Critical, LEUKEMIA")
    open fun updatePreExistenceDiseaseExistent(idPreExistenceDisease: Int, newSeverity: String, newType: String) {
        // Arrange
        val url = "/pre-existence-disease/get-one-by-id"
        val resultBefore = mockMvc.perform(
            get(url).
                param("idPreExistenceDisease", idPreExistenceDisease.toString())
                    .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk).andReturn()
        val contentBefore = resultBefore.response.contentAsString
        val jsonBefore = objectMapper.readTree(contentBefore)
        println("Respuesta ANTERIOR: $contentBefore")

        // Arrange
        val preExistenceDiseaseDTO = PreExistenceDiseaseDTO(
            id = jsonBefore["id"].asInt(),
            medicalHistoryId = jsonBefore["medicalHistoryId"].asInt(),
            isActive = jsonBefore["active"].asBoolean(),
            observation = jsonBefore["observation"].asText(),
            diagnosisDate = jsonBefore["diagnosisDate"].asText(),
            severity = newSeverity,
            type = newType
        )
        val updatedJson = objectMapper.writeValueAsString(preExistenceDiseaseDTO)

        // Act
        mockMvc.perform(put("/pre-existence-disease/update").contentType(MediaType.APPLICATION_JSON).content(updatedJson)).andExpect(status().isOk)
        val resultAfter = mockMvc.perform(
            get(url)
                .param("idPreExistenceDisease", idPreExistenceDisease.toString())
                    .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk).andReturn()
        val contentAfter = resultAfter.response.contentAsString
        val jsonAfter = objectMapper.readTree(contentAfter)
        println("Respuesta ACTUALIZADA: $contentAfter")

        //Assert
        assertEquals(newSeverity, jsonAfter["severity"].asText())
        assertEquals(newType, jsonAfter["type"].asText())
    }

}