package com.kkm.recetas.repository

import com.kkm.recetas.data.local.model.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count

class RecipesRepository(
    private val recipesRemoteDataSource: RecipesRemoteDataSource,
    private val recipesLocalDataSource: RecipesLocalDataSource
) {
    val recipes: Flow<List<Recipe>> = recipesLocalDataSource.localRecipes

    suspend fun getRecipe() {
        val request = recipesRemoteDataSource.getRecipe()
        recipesLocalDataSource.insertRecipe(request)
    }

    suspend fun deleteRecipe(recipe: Recipe) {
        recipesLocalDataSource.deleteRecipe(recipe)
    }
}
