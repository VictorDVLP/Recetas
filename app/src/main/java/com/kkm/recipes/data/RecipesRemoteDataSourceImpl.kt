package com.kkm.recipes.data

import com.kqm.architectureclean.domain.Recipe

interface RecipesRemoteDataSourceImpl {
    suspend fun getRecipe(): List<Recipe>
}