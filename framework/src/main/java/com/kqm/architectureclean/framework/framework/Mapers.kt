package com.kqm.architectureclean.framework.framework

import com.kqm.architectureclean.domain.Ingredient
import com.kqm.architectureclean.domain.IngredientMeasure
import com.kqm.architectureclean.domain.Recipe
import com.kqm.architectureclean.framework.framework.local.database.LocalIngredient
import com.kqm.architectureclean.framework.framework.local.database.LocalMeasure
import com.kqm.architectureclean.framework.framework.local.database.LocalRecipe
import com.kqm.architectureclean.framework.framework.local.database.RecipeWithIngredientsAndMeasures
import com.kqm.architectureclean.framework.framework.remote.entities.Meal


fun Recipe.toRecipeWithIngredientsAndMeasures(): RecipeWithIngredientsAndMeasures {

    val localRecipe = LocalRecipe(
        id = id,
        name = name,
        area = area,
        category = category,
        instructions = instructions,
        imageThumb = imageThumb,
        videoThumb = videoThumb,
        isFavorite = favorite
    )

    val localIngredients = ingredientMeasures.map { it.ingredient.toLocalIngredient() }
    val localMeasures = ingredientMeasures.map { it.toLocalMeasure(localRecipe.id) }
    return RecipeWithIngredientsAndMeasures(
        recipe = localRecipe,
        ingredients = localIngredients,
        measures = localMeasures
    )
}

fun Ingredient.toLocalIngredient(): LocalIngredient {
    return LocalIngredient(name = name)
}

fun IngredientMeasure.toLocalMeasure(recipeId: String): LocalMeasure {
    return LocalMeasure(ingredientId = ingredient.name, measure = measure, recipeId = recipeId)
}


fun RecipeWithIngredientsAndMeasures.toRecipe2(): Recipe {
    val measures = measures.map { it.toMeasure() }
    return Recipe(
        id = recipe.id,
        name = recipe.name,
        category = recipe.category,
        area = recipe.area,
        imageThumb = recipe.imageThumb,
        videoThumb = recipe.videoThumb,
        instructions = recipe.instructions,
        ingredientMeasures = measures,
        favorite = recipe.isFavorite
    )
}

fun LocalMeasure.toMeasure(): IngredientMeasure {
    return IngredientMeasure(ingredient = Ingredient(ingredientId), measure = measure)
}


fun Meal.toRecipe(): Recipe {

    val ingredients: List<Ingredient> = listOfNotNull(
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
    ).filter { it.isNotEmpty() }.map { Ingredient(it) }

    val preMeasures: List<String> = listOfNotNull(
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
    ).filter { it.isNotEmpty() }

    val measures: List<IngredientMeasure> = ingredients.zip(preMeasures) {
        ingredient, measures -> IngredientMeasure(ingredient, measures)
    }

    return Recipe(
        id = idMeal,
        name = strMeal,
        category = strCategory,
        area = strArea,
        imageThumb = strMealThumb,
        videoThumb = strYoutube,
        instructions = strInstructions,
        ingredientMeasures = measures,
        favorite = false
    )
}