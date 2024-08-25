package com.kqm.architectureclean.data

import com.kqm.architectureclean.domain.Recipe

interface RecipesRemoteDataSourceImpl {
    suspend fun getRecipe(): Recipe
}