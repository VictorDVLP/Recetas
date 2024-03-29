package com.kkm.recetas.data.local.model

data class Recipe(
    val id: String,
    val name: String,
    val imageThumb: String,
    val videoThumb: String,
    val area: String,
    val category: String,
    val instructions: String,
    val ingredients: List<String>,
    val measures: List<String>
   // val sourceUrl: String
)
