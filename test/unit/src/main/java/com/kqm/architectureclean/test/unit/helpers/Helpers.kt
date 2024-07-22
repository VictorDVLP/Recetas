package com.kqm.architectureclean.test.unit.helpers

import com.kqm.architectureclean.domain.Recipe

fun generateRecipes(count: Int): List<Recipe> {
    return (1..count).map { generateRecipe(it) }
}

private fun generateRecipe(id: Int): Recipe {
    return Recipe(
        id = "recipe_$id",
        name = "Recipe $id",
        imageThumb = "image_$id.jpg",
        videoThumb = "video_$id.mp4",
        area = "Area $id",
        category = "Category $id",
        instructions = "Instructions for recipe $id",
        ingredients = listOf("Ingredient ${id}_1", "Ingredient ${id}_2"),
        measures = listOf("Measure ${id}_1", "Measure ${id}_2"),
        favorite = false
    )
}