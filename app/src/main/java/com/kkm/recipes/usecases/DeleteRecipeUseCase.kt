package com.kkm.recipes.usecases

import com.kkm.recipes.data.RecipesRepository
import com.kqm.architectureclean.domain.Recipe

class DeleteRecipeUseCase(private val repository: RecipesRepository) {

    suspend operator fun invoke(recipe: Recipe) = repository.deleteRecipe(recipe)
}
