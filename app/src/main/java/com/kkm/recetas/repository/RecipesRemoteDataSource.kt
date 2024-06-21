package com.kkm.recetas.repository

import com.kkm.recetas.domain.Recipe
import com.kkm.recetas.data.remote.RecetasApi
import com.kkm.recetas.extensions.toRecipe

interface RecipesRemoteDataSourceImpl {
    suspend fun getRecipe(): List<Recipe>
}

class RecipesRemoteDataSource: RecipesRemoteDataSourceImpl {
    override suspend fun getRecipe(): List<Recipe> {
        val result = RecetasApi.retrofitService.getRecipes()
        return result.meals.first().toRecipe()
    }
}
