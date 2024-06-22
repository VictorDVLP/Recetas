package com.kkm.recipes.usecases

import com.kkm.recipes.data.RecipesRepository
import com.kqm.architectureclean.domain.Recipe
import kotlinx.coroutines.flow.Flow

class GetAllRecipesUseCase(private val repository: RecipesRepository) {

    operator fun invoke(): Flow<List<Recipe>> = repository.recipes
}