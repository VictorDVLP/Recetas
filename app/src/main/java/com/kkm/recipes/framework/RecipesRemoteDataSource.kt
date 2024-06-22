package com.kkm.recipes.framework

import com.kkm.recipes.data.RecipesRemoteDataSourceImpl
import com.kkm.recipes.domain.Recipe
import com.kkm.recipes.framework.remote.RecipesApi

class RecipesRemoteDataSource(private val recipesApi: RecipesApi) : RecipesRemoteDataSourceImpl {
    override suspend fun getRecipe(): List<Recipe> {
        val result = recipesApi.retrofitService.getRecipes()
        return result.meals.first().toRecipe()
    }
}
