package com.kkm.recipes.usecases

import com.kqm.architectureclean.data.RecipesRepository
import com.kqm.architectureclean.domain.Recipe

class DeleteRecipeUseCase(private val repository: RecipesRepository) {

    suspend operator fun invoke(recipe: Recipe) = repository.deleteRecipe(recipe)
}
