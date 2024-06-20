package com.kkm.recetas.usecases

import com.kkm.recetas.domain.Recipe
import com.kkm.recetas.repository.RecipesRepository
import kotlinx.coroutines.flow.Flow

class GetAllRecipesUseCase(private val repository: RecipesRepository) {

    operator fun invoke(): Flow<List<Recipe>> = repository.recipes
}