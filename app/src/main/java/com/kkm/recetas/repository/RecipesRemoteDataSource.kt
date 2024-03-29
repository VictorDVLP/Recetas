package com.kkm.recetas.repository

import com.kkm.recetas.data.local.model.Recipe
import com.kkm.recetas.data.remote.RecetasApi
import com.kkm.recetas.extensions.toRecipe

class RecipesRemoteDataSource {
    suspend fun getRecipe(): List<Recipe> {
        val result = RecetasApi.retrofitService.getRecipes()
        return result.meals.map { it.toRecipe() }
    }
}
