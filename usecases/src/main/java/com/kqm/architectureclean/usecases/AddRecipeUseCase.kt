package com.kqm.architectureclean.usecases

import com.kqm.architectureclean.data.RecipesRepository
import javax.inject.Inject

class AddRecipeUseCase @Inject constructor(private val repository: RecipesRepository) {
    suspend operator fun invoke() = repository.addRecipe()
}
