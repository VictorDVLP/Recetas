package com.kkm.recetas.usecases

import com.kkm.recetas.repository.RecipesRepository

class AddRecipeUseCase(private val repository: RecipesRepository) {
    suspend operator fun invoke() = repository.addRecipe()
}
