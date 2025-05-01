package ar.edu.unsam.proyecto.vetappbackend.service.shift

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import ar.edu.unsam.proyecto.vetappbackend.service.BaseService
import ar.edu.unsam.proyecto.vetappbackend.domain.shift.Recipe
import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.repository.shift.RecipeRepository

@Service
class RecipeService: BaseService<Recipe> {

    @Autowired lateinit var recipeRepository: RecipeRepository

    override fun getOneById(recipeId: Int): Recipe {
        return this.recipeRepository.findById(recipeId).orElseThrow {
            NotFoundException("No se encontró la receta indicada: $recipeId")
        }
    }

    override fun getAll(): List<Recipe> {
        return this.recipeRepository.findAll().toList()
    }

    override fun create(recipe: Recipe) {
        this.recipeRepository.save(recipe)
    }

    override fun delete(recipe: Recipe) {
        this.recipeRepository.delete(recipe)
    }

    override fun update(recipeUpdate: Recipe) {
        this.getOneById(recipeUpdate.id!!)
        recipeRepository.save(recipeUpdate)
    }

}