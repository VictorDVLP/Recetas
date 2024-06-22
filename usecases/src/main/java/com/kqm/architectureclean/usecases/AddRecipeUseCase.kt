package com.kqm.architectureclean.usecases

import com.kqm.architectureclean.data.RecipesRepository

class AddRecipeUseCase(private val repository: RecipesRepository) {
    suspend operator fun invoke() = repository.addRecipe()
}
