package com.kkm.recetas.usecases

import com.kkm.recetas.domain.Recipe
import com.kkm.recetas.repository.RecipesRepository

class UpdateFavoriteUseCase(private val repository: RecipesRepository) {

    suspend operator fun invoke(recipe: Recipe) {
        repository.updateFavorite(recipe)
    }
}
