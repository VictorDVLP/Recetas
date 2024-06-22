package com.kkm.recetas.usecases

import com.kkm.recetas.data.RecipesRepository
import com.kkm.recetas.domain.Recipe

class DeleteRecipeUseCase(private val repository: RecipesRepository) {

    suspend operator fun invoke(recipe: Recipe) = repository.deleteRecipe(recipe)
}
