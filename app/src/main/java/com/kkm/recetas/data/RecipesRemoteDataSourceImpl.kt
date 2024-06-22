package com.kkm.recetas.data

import com.kkm.recetas.domain.Recipe

interface RecipesRemoteDataSourceImpl {
    suspend fun getRecipe(): List<Recipe>
}