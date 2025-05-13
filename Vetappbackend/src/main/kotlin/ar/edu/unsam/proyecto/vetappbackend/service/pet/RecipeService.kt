package ar.edu.unsam.proyecto.vetappbackend.service.pet

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.Recipe
import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.repository.pet.RecipeRepository
import ar.edu.unsam.proyecto.vetappbackend.service.BaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RecipeService: BaseService<Recipe> {

    @Autowired
    lateinit var recipeRepository: RecipeRepository

    override fun getOneById(idRecipe: Int): Recipe {
        return this.recipeRepository.findById(idRecipe).orElseThrow {
            NotFoundException("No se encontró la receta indicada: $idRecipe")
        }
    }

    override fun getAll(): List<Recipe> {
        return this.recipeRepository.findAll().toList()
    }

    override fun create(newRecipe: Recipe) {
        this.recipeRepository.save(newRecipe)
    }

    override fun delete(recipeDelete: Recipe) {
        this.recipeRepository.delete(recipeDelete)
    }

    override fun update(recipeUpdate: Recipe) {
        recipeRepository.save(recipeUpdate)
    }

}