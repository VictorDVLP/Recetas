package com.kkm.recipes.data

import com.kkm.recipes.domain.Recipe

interface RecipesRemoteDataSourceImpl {
    suspend fun getRecipe(): List<Recipe>
}