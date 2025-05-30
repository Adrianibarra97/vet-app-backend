package ar.edu.unsam.proyecto.vetappbackend.pet

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.MedicalHistory
import ar.edu.unsam.proyecto.vetappbackend.dto.pet.PetDTO
import ar.edu.unsam.proyecto.vetappbackend.repository.pet.MedicalHistoryRepository
import ar.edu.unsam.proyecto.vetappbackend.repository.pet.PetRepository
import ar.edu.unsam.proyecto.vetappbackend.repository.user.PetOwnerRepository
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.transaction.Transactional
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.http.MediaType
import java.time.LocalDate

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Tag("integration")
class PetControllerIntegrationTest {

    @Autowired
    private lateinit var petOwnerRepository: PetOwnerRepository

    @Autowired
    private lateinit var petRepository: PetRepository

    @Autowired
    private lateinit var mockMvc: MockMvc //Permite simular peticiones HTTP.

    @Autowired
    lateinit var objectMapper: ObjectMapper //Convierto objetos a JSON.

    @Test
    @DisplayName("Debe devolver todos los pets del sistema")
    @Transactional
    fun getAllPets_debeDevolverListaDePets() {
        // Arrange
        val url = "/pet/get-all"

        // Act & Assert
        mockMvc.perform(get(url))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$").isArray)
            .andExpect(jsonPath("$[0].id").exists())
            .andExpect(jsonPath("$[0].name").exists())
            .andExpect(jsonPath("$[0].specie").exists())
            .andExpect(jsonPath("$[0].breed").exists())
            .andDo { result ->
                println("Mascotas recibidas: ${result.response.contentAsString}")
            }
    }

    @Test
    @DisplayName("Debe devolver un pet existente según su ID")
    @Transactional
    fun getOneById_debeDevolverPetCorrecto() {
        // Arrange
        val idPet = 1 // Asegurate de que este ID exista en tu base de datos de prueba
        val url = "/pet/get-one-by-id"

        // Act & Assert
        mockMvc.perform(get(url).param("idPet", idPet.toString()))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(idPet))
            .andExpect(jsonPath("$.name").exists())
            .andExpect(jsonPath("$.specie").exists())
            .andExpect(jsonPath("$.petOwnerId").exists())
            .andDo { result ->
                println("Mascota recibida: ${result.response.contentAsString}")
            }
    }

    @Test
    @DisplayName("Debe fallar al intentar obtener un Pet con ID incorrecto")
    @Transactional
    fun getOneById_debeFallarCuandoIdPetEsIncorrecto() {
        // Arrange
        val idPet = -1
        val url = "/pet/get-one-by-id"

        // Act & Assert
        mockMvc.perform(get(url).param("idPet", idPet.toString()))
            .andExpect(status().isNotFound)
            .andDo { result ->
                println("Respuesta al buscar mascota con ID inválido: ${result.response.contentAsString}")
            }
    }


    @DisplayName("Debe crear un nuevo Pet correctamente")
    @Transactional
    @ParameterizedTest(name = "ID petOwnerId = {0}")
    @CsvSource(
        "1"
    )
    fun createPet_debeCrearNuevoPet(petOwnerId: Int) {
        // Arrange
        val owner = petOwnerRepository.findById(petOwnerId).orElseThrow { RuntimeException("Dueño no encontrado") }

        val medicalHistory = MedicalHistory().apply {
            summary = "El pet está bien."
            createdAt = LocalDate.now()
            updatedAt = LocalDate.now()
        }

        val newPet = PetDTO(
            id = null,
            age = 2,
            name = "Firulais",
            photo = "/src/assets/firulais.jpg",
            breed = "Labrador",
            weight = 12.0,
            birth = "2023-05-26",
            sex = "Macho",
            specie = "DOG",
            sterilized = true,
            idMedicalHistory = null,
            summary = medicalHistory.summary,
            createdAt = medicalHistory.createdAt.toString(),
            updatedAt = medicalHistory.updatedAt.toString(),
            petOwnerId = petOwnerId
        )

        val petJson = objectMapper.writeValueAsString(newPet)

        // Act & Assert
        mockMvc.perform(
            post("/pet/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(petJson)
        )
            .andExpect(status().isOk)
            .andReturn()

        val response = mockMvc.perform(get("/pet/get-all"))
            .andExpect(status().isOk)
            .andReturn()

        println("Mascotas existentes: ${response.response.contentAsString}")

        val petCreado = petRepository.findAll().find { it.name == "Firulais" }

        println("Mascota creada: $petCreado")
    }

    @DisplayName("Debe actualizar un Pet existente correctamente")
    @Transactional
    @ParameterizedTest(name = "ID petId = {0}")
    @CsvSource("1")
    fun updatePet_debeActualizarPetExistente(petId: Int) {
        // Arrange
        val existingPet = petRepository.findById(petId).orElseThrow { RuntimeException("Pet no encontrado") }
        val petOwner = existingPet.petOwner?.let { petOwnerRepository.findById(it.id).orElseThrow { RuntimeException("Dueño no encontrado") } }

        val updatedPetDTO = existingPet.medicalHistory?.let {
            PetDTO(
                id = existingPet.id,
                age = existingPet.age,
                name = "Firulais Actualizado",
                photo = existingPet.photo,
                breed = existingPet.breed,
                weight = 15.0,
                birth = existingPet.birth.toString(),
                sex = existingPet.sex.toString(),
                specie = existingPet.specie.toString(),
                sterilized = existingPet.sterilized,
                idMedicalHistory = existingPet.medicalHistory?.id,
                summary = it.summary,
                createdAt = existingPet.medicalHistory!!.createdAt.toString(),
                updatedAt = LocalDate.now().toString(),  // actualizar fecha
                petOwnerId = petOwner!!.id
            )
        }

        val updatedPetJson = objectMapper.writeValueAsString(updatedPetDTO)

        // Act
        mockMvc.perform(
            put("/pet/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedPetJson)
        )
            .andExpect(status().isOk)

        // Assert
        val petActualizado = petRepository.findById(petId).orElseThrow()
        assertEquals("Firulais Actualizado", petActualizado.name)
        assertEquals(15.0, petActualizado.weight)
    }

    @ParameterizedTest(name = "ID petId = {0}")
    @CsvSource("-1", "99999") // IDs inexistentes o inválidos
    @DisplayName("Debe fallar al intentar actualizar un Pet inexistente")
    @Transactional
    fun updatePet_debeFallarCuandoPetNoExiste(petId: Int) {
        // Arrange
        val updatedPetDTO = PetDTO(
            id = petId,
            age = 2,
            name = "Firulais Actualizado",
            photo = "/src/assets/firulais.jpg",
            breed = "Labrador",
            weight = 15.0,
            birth = "2023-05-26",
            sex = "Macho",
            specie = "DOG",
            sterilized = true,
            idMedicalHistory = null,
            summary = "Actualización fallida",
            createdAt = LocalDate.now().toString(),
            updatedAt = LocalDate.now().toString(),
            petOwnerId = 1
        )

        val updatedPetJson = objectMapper.writeValueAsString(updatedPetDTO)

        // Act & Assert
        mockMvc.perform(
            put("/pet/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedPetJson)
        )
            .andExpect(status().isNotFound)
            .andDo { result ->
                println("Respuesta al intentar actualizar un Pet inexistente: ${result.response.contentAsString}")
            }
    }

    @DisplayName("Debe eliminar un Pet existente correctamente")
    @Transactional
    @ParameterizedTest(name = "ID petId = {0}")
    @CsvSource("1")
    fun deletePet_debeEliminarPetExistente(petId: Int) {
        // Arrange
        val existingPet = petRepository.findById(petId).orElseThrow { RuntimeException("Pet no encontrado") }

        // Act
        mockMvc.perform(
            delete("/pet/delete")
                .param("idPet", petId.toString())
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)

        // Assert
        val petExistenteDespues = petRepository.findById(petId)
        assertTrue(petExistenteDespues.isEmpty, "El pet debería haber sido eliminado")
    }



}