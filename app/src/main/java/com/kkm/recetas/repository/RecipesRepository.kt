package com.kkm.recetas.repository

import com.kkm.recetas.data.local.model.Recipe

class RecipesRepository(
    private val recipesRemoteDataSource: RecipesRemoteDataSource,
    private val recipesLocalDataSource: RecipesLocalDataSource
) {
    suspend fun getRecipe(): List<Recipe> {
        return recipesRemoteDataSource.getRecipe()
    }
}
