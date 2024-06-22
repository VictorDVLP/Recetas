package com.kkm.recetas.framework

import com.kkm.recetas.data.RecipesRemoteDataSourceImpl
import com.kkm.recetas.domain.Recipe
import com.kkm.recetas.framework.remote.RecipesApi

class RecipesRemoteDataSource(private val recipesApi: RecipesApi) : RecipesRemoteDataSourceImpl {
    override suspend fun getRecipe(): List<Recipe> {
        val result = recipesApi.retrofitService.getRecipes()
        return result.meals.first().toRecipe()
    }
}
