package com.kkm.recipes.framework

import com.kqm.architectureclean.domain.Recipe
import com.kkm.recipes.framework.local.database.LocalRecipes
import com.kkm.recipes.framework.remote.entities.Meal


fun Recipe.toEntity(): LocalRecipes {

    return LocalRecipes(
        id = id.toInt(),
        name = name,
        area = area,
        category = category,
        instructions = instructions,
        imageThumb = imageThumb,
        videoThumb = videoThumb,
        ingredient1 = ingredients.getOrNull(0),
        ingredient2 = ingredients.getOrNull(1),
        ingredient3 = ingredients.getOrNull(2),
        ingredient4 = ingredients.getOrNull(3),
        ingredient5 = ingredients.getOrNull(4),
        ingredient6 = ingredients.getOrNull(5),
        ingredient7 = ingredients.getOrNull(6),
        ingredient8 = ingredients.getOrNull(7),
        ingredient9 = ingredients.getOrNull(8),
        ingredient10 = ingredients.getOrNull(9),
        ingredient11 = ingredients.getOrNull(10),
        ingredient12 = ingredients.getOrNull(11),
        ingredient13 = ingredients.getOrNull(12),
        ingredient14 = ingredients.getOrNull(13),
        ingredient15 = ingredients.getOrNull(14),
        ingredient16 = ingredients.getOrNull(15),
        ingredient17 = ingredients.getOrNull(16),
        ingredient18 = ingredients.getOrNull(17),
        ingredient19 = ingredients.getOrNull(18),
        ingredient20 = ingredients.getOrNull(19),
        measure1 = measures.getOrNull(0),
        measure2 = measures.getOrNull(1),
        measure3 = measures.getOrNull(2),
        measure4 = measures.getOrNull(3),
        measure5 = measures.getOrNull(4),
        measure6 = measures.getOrNull(5),
        measure7 = measures.getOrNull(6),
        measure8 = measures.getOrNull(7),
        measure9 = measures.getOrNull(8),
        measure10 = measures.getOrNull(9),
        measure11 = measures.getOrNull(10),
        measure12 = measures.getOrNull(11),
        measure13 = measures.getOrNull(12),
        measure14 = measures.getOrNull(13),
        measure15 = measures.getOrNull(14),
        measure16 = measures.getOrNull(15),
        measure17 = measures.getOrNull(16),
        measure18 = measures.getOrNull(17),
        measure19 = measures.getOrNull(18),
        measure20 = measures.getOrNull(19),
        isFavorite = isFavorite
    )
}


fun LocalRecipes.toRecipe2(): Recipe {

    val listIngredients: List<String> = listOfNotNull(
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
    )


    val listMeasure: List<String> = listOfNotNull(
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
    )

    return Recipe(
        id = id.toString(),
        name = name,
        area = area,
        category = category,
        ingredients = listIngredients,
        instructions = instructions,
        imageThumb = imageThumb,
        videoThumb = videoThumb,
        measures = listMeasure,
        isFavorite = isFavorite
    )
}

fun Meal.toRecipe(): List<Recipe> {

    val preListIngredients: List<String?> = listOf(
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


    val listIngredients: List<String> =
        preListIngredients.filterNot { it.isNullOrEmpty() }.filterNotNull()

    val preListMeasure: List<String?> = listOf(
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


    val listMeasure: List<String> = preListMeasure.filterNot { it.isNullOrEmpty() }.filterNotNull()

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
            isFavorite = false
        )
    )
}