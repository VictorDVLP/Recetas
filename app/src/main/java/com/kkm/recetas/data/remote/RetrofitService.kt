package com.kkm.recetas.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val URL = "https://www.themealdb.com/api/json/v1/1/"

private val retrofit = Retrofit.Builder()
    .baseUrl(URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object RecetasApi {
    val retrofitService: RecetasService by lazy {
        retrofit.create(RecetasService::class.java)
    }
}