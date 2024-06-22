package com.kkm.recetas.data

import com.kkm.recetas.domain.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipesLocalDataSourceImpl {
    val localRecipes: Flow<List<Recipe>>

    suspend fun insertRecipe(recipe: List<Recipe>)

    suspend fun deleteRecipe(recipe: Recipe)
}