package com.kkm.recetas.data.remote

import com.kkm.recetas.data.remote.entities.Result
import retrofit2.http.GET

interface RecetasService {

    @GET("random.php")
    suspend fun getRecipes(): Result
}
