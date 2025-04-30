package ar.edu.unsam.proyecto.vetappbackend.service

import ar.edu.unsam.proyecto.vetappbackend.domain.shift.Recipe
import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.repository.RecipeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RecipeService: BaseService<Recipe> {

    @Autowired lateinit var recipeRepository: RecipeRepository
    override fun getAll(): List<Recipe> {
        return this.recipeRepository.findAll().toList()
    }

    override fun getOneById(recipeId: Int): Recipe {
        return this.recipeRepository.findById(recipeId).orElseThrow {
            NotFoundException("No se encontró la receta indicada: $recipeId")
        }
    }

    override fun create(recipe: Recipe) {
        this.recipeRepository.save(recipe)
    }

    override fun update(recipeUpdate: Recipe) {
        val recipe: Recipe = this.getOneById(recipeUpdate.id!!)
        recipe.apply {
            this.id = recipeUpdate.id!!
            this.description = recipeUpdate.description
            this.medicalShift = recipeUpdate.medicalShift
        }
        recipeRepository.save(recipe)
    }

    override fun delete(recipe: Recipe) {
        this.recipeRepository.delete(recipe)
    }

}