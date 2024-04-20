package com.kkm.recetas.repository

import android.util.Log
import com.kkm.recetas.data.local.model.Recipe
import com.kkm.recetas.data.remote.RecetasApi
import com.kkm.recetas.extensions.toRecipe

class RecipesRemoteDataSource {
    suspend fun getRecipe(): Recipe {
        val result = RecetasApi.retrofitService.getRecipes()
        Log.d("TAG", "RemoteDataSource: $result")
        return result.meals.last().toRecipe()
    }
}
