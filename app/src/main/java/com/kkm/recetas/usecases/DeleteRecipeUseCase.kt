package com.kkm.recetas.usecases

import com.kkm.recetas.domain.Recipe
import com.kkm.recetas.repository.RecipesRepository

class DeleteRecipeUseCase(private val repository: RecipesRepository) {

    suspend operator fun invoke(recipe: Recipe) = repository.deleteRecipe(recipe)
}
