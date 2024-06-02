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
        instructions = instructions,
        imageThumb = imageThumb,
        videoThumb = videoThumb,
        ingredient1 = ingredients[0],
        ingredient2 = ingredients[1],
        ingredient3 = ingredients[2],
        ingredient4 = ingredients[3],
        ingredient5 = ingredients[4],
        ingredient6 = ingredients[5],
        ingredient7 = ingredients[6],
        ingredient8 = ingredients[7],
        ingredient9 = ingredients[8],
        ingredient10 = ingredients[9],
        ingredient11 = ingredients[10],
        ingredient12 = ingredients[11],
        ingredient13 = ingredients[12],
        ingredient14 = ingredients[13],
        ingredient15 = ingredients[14],
        ingredient16 = ingredients[15],
        ingredient17 = ingredients[16],
        ingredient18 = ingredients[17],
        ingredient19 = ingredients[18],
        ingredient20 = ingredients[19],
        measure1 = measures[0],
        measure2 = measures[1],
        measure3 = measures[2],
        measure4 = measures[3],
        measure5 = measures[4],
        measure6 = measures[5],
        measure7 = measures[6],
        measure8 = measures[7],
        measure9 = measures[8],
        measure10 = measures[9],
        measure11 = measures[10],
        measure12 = measures[11],
        measure13 = measures[12],
        measure14 = measures[13],
        measure15 = measures[14],
        measure16 = measures[15],
        measure17 = measures[16],
        measure18 = measures[17],
        measure19 = measures[18],
        measure20 = measures[19]
    )
}


fun LocalRecipes.toRecipe2(): Recipe {

    val listIngredients: List<String> = listOf(
        ingredient1,
        ingredient2,
        ingredient3,
        ingredient4,
        ingredient5,
        ingredient6,
        ingredient7,
        ingredient8,
        ingredient9,
        ingredient10,
        ingredient11,
        ingredient12,
        ingredient13,
        ingredient14,
        ingredient15,
        ingredient16,
        ingredient17,
        ingredient18,
        ingredient19,
        ingredient20
    ).mapNotNull { it }

    val listMeasure: List<String> = listOf(
        measure1,
        measure2,
        measure3,
        measure4,
        measure5,
        measure6,
        measure7,
        measure8,
        measure9,
        measure10,
        measure11,
        measure12,
        measure13,
        measure14,
        measure15,
        measure16,
        measure17,
        measure18,
        measure19,
        measure20
    ).mapNotNull { it }

    return Recipe(
        id = id.toString(),
        name = name,
        area = area,
        category = category,
        ingredients = listIngredients,
        instructions = instructions,
        imageThumb = imageThumb,
        videoThumb = videoThumb,
        measures = listMeasure
    )
}

fun Meal.toRecipe(): List<Recipe> {

    val listIngredients: List<String> = listOfNotNull(
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

    val listMeasure: List<String> = listOfNotNull(
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

    return listOf(
        Recipe(
            id = idMeal,
            name = strMeal,
            category = strCategory,
            area = strArea,
            imageThumb = strMealThumb,
            videoThumb = strYoutube,
            instructions = strInstructions,
            ingredients = listIngredients,
            measures = listMeasure,
        )
    )
}