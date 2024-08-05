package com.architectureclean.recipes.framework

import com.architectureclean.recipes.framework.remote.RecipesApi
import com.kqm.architectureclean.data.RecipesRemoteDataSourceImpl
import com.kqm.architectureclean.domain.Recipe
import javax.inject.Inject

class RecipesRemoteDataSource @Inject constructor(private val recipesApi: RecipesApi) : RecipesRemoteDataSourceImpl {
    override suspend fun getRecipe(): List<Recipe> {
        val result = recipesApi.retrofitService.getRecipes()
        return result.meals.first().toRecipe()
    }
}
