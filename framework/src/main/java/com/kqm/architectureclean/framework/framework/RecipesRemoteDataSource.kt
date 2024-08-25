package com.kqm.architectureclean.framework.framework

import com.kqm.architectureclean.framework.framework.remote.RecipesApi
import com.kqm.architectureclean.data.RecipesRemoteDataSourceImpl
import com.kqm.architectureclean.domain.Recipe
import javax.inject.Inject

class RecipesRemoteDataSource @Inject constructor(private val recipesApi: RecipesApi) : RecipesRemoteDataSourceImpl {
    override suspend fun getRecipe(): Recipe {
        val result = recipesApi.retrofitService.getRecipes()
        return result.meals[0].toRecipe()
    }
}
