package com.architectureclean.recipes.framework.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val URL = "https://www.themealdb.com/api/json/v1/1/"

internal object RecipesApi {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: RecipesService by lazy {
        retrofit.create(RecipesService::class.java)
    }
}