package com.kqm.architectureclean.usecases

import com.kqm.architectureclean.data.RecipesRepository
import com.kqm.architectureclean.domain.Recipe
import javax.inject.Inject

class UpdateFavoriteUseCase @Inject constructor(private val repository: RecipesRepository) {

    suspend operator fun invoke(recipe: Recipe) {
        repository.updateFavorite(recipe)
    }
}
