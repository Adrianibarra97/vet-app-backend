package ar.edu.unsam.proyecto.vetappbackend.repository.pet

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.Recipe
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RecipeRepository: CrudRepository<Recipe, Int>