package com.kkm.recetas.data.remote

import com.kkm.recetas.data.remote.entities.Result
import retrofit2.http.GET

interface RecipesService {

    @GET("random.php")
   suspend fun getRecipes(): Result
}
