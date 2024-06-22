package com.kkm.recetas.framework.remote

import com.kkm.recetas.framework.remote.entities.Result
import retrofit2.http.GET

interface RecipesService {

    @GET("random.php")
    suspend fun getRecipes(): Result
}
