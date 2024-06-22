package com.kqm.architectureclean.usecases

import com.kqm.architectureclean.data.RecipesRepository
import com.kqm.architectureclean.domain.Recipe
import kotlinx.coroutines.flow.Flow

class GetAllRecipesUseCase(private val repository: RecipesRepository) {

    operator fun invoke(): Flow<List<Recipe>> = repository.recipes
}