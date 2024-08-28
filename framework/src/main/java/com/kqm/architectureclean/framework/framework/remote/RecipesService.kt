package com.kqm.architectureclean.framework.framework.remote

import com.kqm.architectureclean.framework.framework.remote.entities.Result
import retrofit2.http.GET

interface RecipesService {

    @GET("random.php")
    suspend fun getRecipes(): Result
}
