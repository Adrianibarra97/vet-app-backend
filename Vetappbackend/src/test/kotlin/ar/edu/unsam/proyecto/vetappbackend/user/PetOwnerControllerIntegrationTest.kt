package ar.edu.unsam.proyecto.vetappbackend.user
import ar.edu.unsam.proyecto.vetappbackend.dto.user.toDTO
import ar.edu.unsam.proyecto.vetappbackend.repository.user.PetOwnerRepository
import org.springframework.beans.factory.annotation.Autowired
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.context.ActiveProfiles
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Tag("integration")
open class PetOwnerControllerIntegrationTest {

    @Autowired private lateinit var mockMvc: MockMvc

    @Autowired lateinit var objectMapper: ObjectMapper

    @Autowired private lateinit var petOwnerRepository: PetOwnerRepository

    @BeforeEach open fun setup() { }


    @Test
    @DisplayName("Debe devolver una lista de dueños de las mascotas")
    open fun returnAllPetOwners() {
        val url = "/pet-owner/get-all"
        val result = mockMvc.perform(get(url)).andExpect(status().isOk).andExpect(jsonPath("$.length()").isNumber).andReturn()
        println(result.response.contentAsString)
    }


    @ParameterizedTest(name = "ID valid petowner = {0}, Username = {1}")
    @CsvSource("1, Eche",  "2, Caro",  "3, Tami",  "4, LuckR")
    @DisplayName("Debe retornar el dueños de la mascota cuando el ID es válido")
    open fun returnPetOwnerFindById(idPetOwner: Int, username: String) {

        val url = "/pet-owner/get-one-by-id"

        val result = mockMvc.perform(get(url).param("idPetOwner", idPetOwner.toString()))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn()

        val json = objectMapper.readTree(result.response.contentAsString)

        println(result.response.contentAsString)

        assertEquals(idPetOwner.toInt(), json["id"].asInt())
        assertEquals(json["username"].asText(), username)
        assertNotNull(json["id"])
        assertNotNull(json["idInfoLocation"])
        assertNotNull(json["idAuthCredentials"])
    }


    @ParameterizedTest(name = "ID invalid petowner = {0}")
    @CsvSource("-1, 0, 900")
    @DisplayName("Debe devolver error 404 cuando el dueño de la mascota no existe")
    open fun returnPetOwnerWhenNoExists(idPetOwner: Int){

        val result = mockMvc.perform(
            get("/pet-owner/get-one-by-id").param("idPetOwner", idPetOwner.toString()))
            .andExpect(status().isNotFound)
            .andReturn()

        assertEquals("No se encontró los datos del usuario: $idPetOwner", result.resolvedException?.message)
    }


    @ParameterizedTest(name = "ID valid petowner = {0}, Username = {1}, NewUsername")
    @CsvSource("1, Eche, ElEche")
    @DisplayName("Debe actualizar el username del dueño de la mascota correctamente")
    open fun returnPutUsernamePetOwner(idPetOwner: Int, username: String, newUsername: String) {
        val putUrl = "/pet-owner/update"
        val getUrl = "/pet-owner/get-one-by-id"

        val result1 = mockMvc.perform(get(getUrl).param("idPetOwner", idPetOwner.toString())).andExpect(status().isOk).andReturn()
        val json1 = objectMapper.readTree(result1.response.contentAsString)
        assertEquals(json1["username"].asText(), username)

        // Arrange
        val petOwnerExisting = petOwnerRepository.findById(idPetOwner).orElseThrow { IllegalStateException("No se encontró el veterinario con ID $idPetOwner") }
        val petOwnerPutDTO = petOwnerExisting.toDTO().copy(username = newUsername)
        val requestJson = objectMapper.writeValueAsString(petOwnerPutDTO)

        // Act
        mockMvc.perform(put(putUrl).contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(status().isOk)

        // Assert
        val result2 = mockMvc.perform(get(getUrl).param("idPetOwner", idPetOwner.toString())).andExpect(status().isOk).andReturn()
        val json2 = objectMapper.readTree(result2.response.contentAsString)
        assertEquals(json2["username"].asText(), newUsername)
        println("Respuesta del dueño de la mascota correctamente actualizado: ${result2.response.contentAsString} ")
    }


    @ParameterizedTest(name = "ID valid petowner = {0}, Username = {1}, NewUsername = {2}")
    @CsvSource("2, Caro, Tami")
    @DisplayName("No debe actualizar el username del dueño de la mascota si el nuevo username está ocupado")
    open fun returnExceptionPutUsernamePetOwner(idPetOwner: Int, username: String, newUsername: String) {
        val putUrl = "/pet-owner/update"
        val getUrl = "/pet-owner/get-one-by-id"

        val resultGet = mockMvc.perform(get(getUrl).param("idPetOwner", idPetOwner.toString())).andExpect(status().isOk).andReturn()
        val json = objectMapper.readTree(resultGet.response.contentAsString)
        assertEquals(username, json["username"].asText())

        // Arrange
        val petOwnerExisting = petOwnerRepository.findById(idPetOwner).orElseThrow { IllegalStateException("No se encontró el dueño de mascota con ID $idPetOwner") }
        val petOwnerPutDTO = petOwnerExisting.toDTO().copy(username = newUsername)
        val requestJson = objectMapper.writeValueAsString(petOwnerPutDTO)

        // Act
        mockMvc.perform(put(putUrl).contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(status().isBadRequest) // <- Esta línea valida el 400.andReturn()
    }


    @DisplayName("Debe devolver a Morena como la unica mascota de Lucas Rodriguez.")
    @ParameterizedTest(name = "ID valid petowner = {0}, NamePet = {1}")
    @CsvSource("4, Morena")
    @Transactional
    open fun getAllPetsOwner(idPetOwner: Int, namePet: String) {
        // Arrange
        val url = "/pet-owner/get-all-pets"
        // Act
        val resultGet = mockMvc.perform(get(url).param("idPetOwner", idPetOwner.toString())).andExpect(status().isOk).andReturn()
        val json = objectMapper.readTree(resultGet.response.contentAsString)
        assertEquals(1, json.size())
        // Assert
        val firstPet = json[0]
        assertEquals(idPetOwner.toString(), firstPet["petOwnerId"].asText())
        assertEquals(namePet, firstPet["name"].asText())
    }

}