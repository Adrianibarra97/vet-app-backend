package ar.edu.unsam.proyecto.vetappbackend

import ar.edu.unsam.proyecto.vetappbackend.service.user.MedicalShiftService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
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
    fun medicalShiftService_shouldReturnMedicalShifts() {
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

}