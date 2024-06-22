package com.kkm.recetas.usecases

import com.kkm.recetas.data.RecipesRepository
import com.kkm.recetas.domain.Recipe

class UpdateFavoriteUseCase(private val repository: RecipesRepository) {

    suspend operator fun invoke(recipe: Recipe) {
        repository.updateFavorite(recipe)
    }
}
