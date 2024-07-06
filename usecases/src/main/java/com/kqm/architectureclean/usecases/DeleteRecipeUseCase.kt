package com.kqm.architectureclean.usecases

import com.kqm.architectureclean.data.RecipesRepository
import com.kqm.architectureclean.domain.Recipe
import javax.inject.Inject

class DeleteRecipeUseCase @Inject constructor(private val repository: RecipesRepository) {

    suspend operator fun invoke(recipe: Recipe) = repository.deleteRecipe(recipe)
}
