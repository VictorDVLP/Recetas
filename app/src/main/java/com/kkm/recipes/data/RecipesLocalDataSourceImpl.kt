package com.kkm.recipes.data

import com.kqm.architectureclean.domain.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipesLocalDataSourceImpl {
    val localRecipes: Flow<List<Recipe>>

    suspend fun insertRecipe(recipe: List<Recipe>)

    suspend fun deleteRecipe(recipe: Recipe)
}