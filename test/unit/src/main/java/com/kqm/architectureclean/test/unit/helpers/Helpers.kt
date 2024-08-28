package com.kqm.architectureclean.test.unit.helpers

import com.kqm.architectureclean.domain.Ingredient
import com.kqm.architectureclean.domain.IngredientMeasure
import com.kqm.architectureclean.domain.Recipe

fun generateRecipes(count: Int): List<Recipe> {
    return (1..count).map { generateRecipe(it) }
}

private fun generateRecipe(id: Int): Recipe {
    return Recipe(
        id = "$id",
        name = "Recipe $id",
        imageThumb = "image_$id.jpg",
        videoThumb = "video_$id.mp4",
        area = "Area $id",
        category = "Category $id",
        instructions = "Instructions for recipe $id",
        ingredientMeasures = listOf(IngredientMeasure(Ingredient("id_${id}_1"), "42g"), IngredientMeasure(Ingredient("id_${id}_2"), "1/2 cup")),
        favorite = false
    )
}