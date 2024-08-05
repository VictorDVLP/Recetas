package com.architectureclean.recipes.framework.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class RecipesApi @Inject constructor(baseUrl: String) {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: RecipesService by lazy {
        retrofit.create(RecipesService::class.java)
    }
}