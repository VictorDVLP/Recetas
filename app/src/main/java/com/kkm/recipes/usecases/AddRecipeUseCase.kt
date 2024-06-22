package com.kkm.recipes.usecases

import com.kqm.architectureclean.data.RecipesRepository

class AddRecipeUseCase(private val repository: RecipesRepository) {
    suspend operator fun invoke() = repository.addRecipe()
}
