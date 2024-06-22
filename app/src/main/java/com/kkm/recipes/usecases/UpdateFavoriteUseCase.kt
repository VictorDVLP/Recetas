package com.kkm.recipes.usecases

import com.kkm.recipes.data.RecipesRepository
import com.kkm.recipes.domain.Recipe

class UpdateFavoriteUseCase(private val repository: RecipesRepository) {

    suspend operator fun invoke(recipe: Recipe) {
        repository.updateFavorite(recipe)
    }
}
