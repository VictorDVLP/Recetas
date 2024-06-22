package com.kkm.recipes.usecases

import com.kkm.recipes.data.RecipesRepository
import com.kkm.recipes.domain.Recipe
import kotlinx.coroutines.flow.Flow

class GetAllRecipesUseCase(private val repository: RecipesRepository) {

    operator fun invoke(): Flow<List<Recipe>> = repository.recipes
}