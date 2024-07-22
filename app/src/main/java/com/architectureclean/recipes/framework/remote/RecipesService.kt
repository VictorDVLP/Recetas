package com.architectureclean.recipes.framework.remote

import com.architectureclean.recipes.framework.remote.entities.Result
import retrofit2.http.GET

interface RecipesService {

    @GET("random.php")
    suspend fun getRecipes(): Result
}
