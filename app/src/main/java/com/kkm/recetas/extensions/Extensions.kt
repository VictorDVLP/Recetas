package com.kkm.recetas.extensions

import com.kkm.recetas.data.local.database.LocalRecipes
import com.kkm.recetas.data.local.model.Recipe
import com.kkm.recetas.data.remote.entities.Meal


fun Recipe.toEntity(): LocalRecipes {

    return LocalRecipes(
        id = id.toInt(),
        name = name,
        area = area,
        category = category,
        ingredients = ingredients,
        instructions = instructions,
        imageThumb = imageThumb,
        videoThumb = videoThumb,
        measures = measures)
}


fun LocalRecipes.toRecipe2(): Recipe {
    return Recipe(
        id = id.toString(),
        name = name,
        area = area,
        category = category,
        ingredients = ingredients,
        instructions = instructions,
        imageThumb = imageThumb,
        videoThumb = videoThumb,
        measures = measures
    )
}

fun Meal.toRecipe(): Recipe {

    val listIngredients: MutableList<String> = mutableListOf(
        strIngredient1,
        strIngredient2,
        strIngredient3,
        strIngredient4,
        strIngredient5,
        strIngredient6,
        strIngredient7,
        strIngredient8,
        strIngredient9,
        strIngredient10,
        strIngredient11,
        strIngredient12,
        strIngredient13,
        strIngredient14,
        strIngredient15,
        strIngredient16,
        strIngredient17,
        strIngredient18,
        strIngredient19,
        strIngredient20
    )

    val listMeasure: MutableList<String> = mutableListOf(
        strMeasure1,
        strMeasure2,
        strMeasure3,
        strMeasure4,
        strMeasure5,
        strMeasure6,
        strMeasure7,
        strMeasure8,
        strMeasure9,
        strMeasure10,
        strMeasure11,
        strMeasure12,
        strMeasure13,
        strMeasure14,
        strMeasure15,
        strMeasure16,
        strMeasure17,
        strMeasure18,
        strMeasure19,
        strMeasure20
    )

    return Recipe(
        id = idMeal,
        name = strMeal,
        category = strCategory,
        area = strArea,
        imageThumb = strMealThumb,
        videoThumb = strYoutube,
        instructions = strInstructions,
        ingredients = listIngredients,
        measures = listMeasure,
        //sourceUrl = strSource
    )
}