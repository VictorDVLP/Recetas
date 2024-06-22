package com.kkm.recetas.repository

import com.kkm.recetas.data.remote.RecipesApi
import com.kkm.recetas.domain.Recipe
import com.kkm.recetas.extensions.toRecipe

interface RecipesRemoteDataSourceImpl {
    suspend fun getRecipe(): List<Recipe>
}

class RecipesRemoteDataSource(private val recipesApi: RecipesApi) : RecipesRemoteDataSourceImpl {
    override suspend fun getRecipe(): List<Recipe> {
        val result = recipesApi.retrofitService.getRecipes()
        return result.meals.first().toRecipe()
    }
}
