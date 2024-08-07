package com.kqm.architectureclean.data

import com.kqm.architectureclean.domain.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipesLocalDataSourceImpl {
    val localRecipes: Flow<List<Recipe>>

    suspend fun insertRecipe(recipe: Recipe)

    suspend fun deleteRecipe(recipe: Recipe)
}