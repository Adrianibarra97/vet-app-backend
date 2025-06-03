package ar.edu.unsam.proyecto.vetappbackend.pet

import ar.edu.unsam.proyecto.vetappbackend.service.pet.RecipeService
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

class RecipeControllerIntegracionTest {

    @Autowired
    private lateinit var recipeService: RecipeService

    @Autowired
    lateinit var mockMvc: MockMvc


    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    @DisplayName("Se obtienen correctamente todas las recipes")
    fun recipes_shouldReturnRecipes() {
        // Arrange
        val url = "/recipe/get-all"

        // Act
        mockMvc.perform(
            get(url)
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn()

    }

    @ParameterizedTest(name = "idRecipe = {0}")
    @CsvSource(
        "1",
        "3"
    )
    @DisplayName("Devuelve correctamente una recipe con id válido")
    fun recipe_shouldReturnRecipe_whenIdValido(idRecipe: Int) {
        // Arrange
        val url = "/recipe/get-one-by-id"

        // Act
        mockMvc.perform(
            get(url)
                .param("idRecipe", idRecipe.toString())
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn()

    }

    @ParameterizedTest(name = "idRecipe = {0}")
    @CsvSource(
        "5",
        "23"
    )
    @DisplayName("Devuelve 404 cuando recipe tiene id inválido")
    fun recipe_shouldReturn404_whenIdInvalido(idRecipe: Int) {
        // Arrange
        val url = "/recipe/get-one-by-id"

        // Act
        mockMvc.perform(
            get(url)
                .param("idRecipe", idRecipe.toString())
        )
            .andExpect(status().isNotFound)

    }

    @ParameterizedTest(name = "id = {0}, description = {1}, medicalHistoryId = {2}, vetId = {3}, date = {4}")
    @CsvSource( "15, 'Receta nueva', 1, 6, '2025-06-02'" )
    @DisplayName("Debe crear correctamente el recipe cuando los datos son válidos")
    fun recipe_shouldCrearRecipe_whenCamposValidos(
        id: Int,
        description: String,
        medicalHistoryId: Int,
        vetId: Int,
        date: String
    ) {

        // Arrange
        val url = "/recipe/create"

        val datos = mapOf(
            "id" to id,
            "description" to description,
            "medicalHistoryId" to medicalHistoryId,
            "vetId" to vetId,
            "date" to date
        )

        val requestBody = objectMapper.writeValueAsString(datos)


        // Act
        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().isOk)

    }

    @ParameterizedTest(name = "id = {0}, description = {1}, medicalHistoryId = {2}, vetId = {3}, date = {4}")
    @CsvSource( "16, 'Receta nueva', 50, 6, '2025-06-02'" )
    @DisplayName("No crea el recipe cuando el id del medical history es invalido")
    fun recipe_shouldNotCrearRecipe_whenMedicalHistoryInvalido(
        id: Int,
        description: String,
        medicalHistoryId: Int,
        vetId: Int,
        date: String
    ) {

        // Arrange
        val url = "/recipe/create"

        val datos = mapOf(
            "id" to id,
            "description" to description,
            "medicalHistoryId" to medicalHistoryId,
            "vetId" to vetId,
            "date" to date
        )

        val requestBody = objectMapper.writeValueAsString(datos)


        // Act
        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().isNotFound)

    }

    @ParameterizedTest(name = "id = {0}, description = {1}, medicalHistoryId = {2}, vetId = {3}, date = {4}")
    @CsvSource( "16, 'Receta nueva', 1, 36, '2025-06-02'" )
    @DisplayName("No crea el recipe cuando el id del veterinario es invalido")
    fun recipe_shouldNotCrearRecipe_whenVetInvalido(
        id: Int,
        description: String,
        medicalHistoryId: Int,
        vetId: Int,
        date: String
    ) {

        // Arrange
        val url = "/recipe/create"

        val datos = mapOf(
            "id" to id,
            "description" to description,
            "medicalHistoryId" to medicalHistoryId,
            "vetId" to vetId,
            "date" to date
        )

        val requestBody = objectMapper.writeValueAsString(datos)


        // Act
        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().isNotFound)

    }

    @ParameterizedTest(name = "id = {0}, description = {1}, medicalHistoryId = {2}, vetId = {3}, date = {4}")
    @CsvSource("4, 'nueva descripcion 2' , 6, 6, '2025-06-02'" )
    @DisplayName("Recipe debe actualizarse correctamente")
    fun recipe_shouldUpdate_whenCamposCorrectos(id: Int, description: String, medicalHistoryId: Int, vetId: Int, date: String) {

        val putUrl = "/recipe/update"

        val recetaActualizada = mapOf(
            "id" to id,
            "description" to description,
            "medicalHistoryId" to medicalHistoryId,
            "vetId" to vetId,
            "date" to date,
        )

        val requestBody = objectMapper.writeValueAsString(recetaActualizada)

        mockMvc.perform(put(putUrl).contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().isOk)

    }

    @ParameterizedTest(name = "id = {0}, description = {1}, medicalHistoryId = {2}, vetId = {3}, date = {4}")
    @CsvSource("4, 'nueva descripcion 2' , 16, 6, '2025-06-02'" )
    @DisplayName("Recipe no puede actualizarse , medical history incorrecto")
    fun recipe_shouldNotUpdate_whenMedicalHistoryIncorrecto(id: Int, description: String, medicalHistoryId: Int, vetId: Int, date: String) {

        val putUrl = "/recipe/update"

        val recetaActualizada = mapOf(
            "id" to id,
            "description" to description,
            "medicalHistoryId" to medicalHistoryId,
            "vetId" to vetId,
            "date" to date,
        )

        val requestBody = objectMapper.writeValueAsString(recetaActualizada)

        mockMvc.perform(put(putUrl).contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().isNotFound)

    }

    @ParameterizedTest(name = "id = {0}, description = {1}, medicalHistoryId = {2}, vetId = {3}, date = {4}")
    @CsvSource("4, 'nueva descripcion 2' , 6, 16, '2025-06-02'" )
    @DisplayName("Recipe no puede actualizarse , veterinario incorrecto")
    fun recipe_shouldNotUpdate_whenVeterinarioIncorrecto(id: Int, description: String, medicalHistoryId: Int, vetId: Int, date: String) {

        val putUrl = "/recipe/update"

        val recetaActualizada = mapOf(
            "id" to id,
            "description" to description,
            "medicalHistoryId" to medicalHistoryId,
            "vetId" to vetId,
            "date" to date,
        )

        val requestBody = objectMapper.writeValueAsString(recetaActualizada)

        mockMvc.perform(put(putUrl).contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().isNotFound)

    }

    @ParameterizedTest(name = "id= {0}")
    @CsvSource("2, 3")
    @DisplayName("Debe eliminar correctamente recipe si existe")
    fun recipe_shouldReturn200_whenEliminaRecipeCorrectamnete(idRecipe: Int) {

        //

        mockMvc.perform(
            delete("/recipe/delete")
                .param("idRecipe", idRecipe.toString())
        )
            .andExpect(status().isOk)
    }


    @ParameterizedTest(name = "id= {0}")
    @CsvSource("12, 23")
    @DisplayName("No puede eliminar correctamente recipe si id no existe")
    fun recipe_shouldReturn404_whenNoPuedeEliminarRecipeIdInvalido(idRecipe: Int) {

        //

        mockMvc.perform(
            delete("/recipe/delete")
                .param("idRecipe", idRecipe.toString())
        )
            .andExpect(status().isNotFound)
    }

}