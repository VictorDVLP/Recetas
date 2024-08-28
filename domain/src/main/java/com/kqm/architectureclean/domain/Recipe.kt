package com.kqm.architectureclean.domain

data class Recipe(
    val id: String,
    val name: String,
    val imageThumb: String,
    val videoThumb: String,
    val area: String,
    val category: String,
    val instructions: String,
    val ingredientMeasures: List<IngredientMeasure>,
    var favorite: Boolean
)

data class Ingredient(val name: String)

data class IngredientMeasure(val ingredient: Ingredient, val measure: String)
