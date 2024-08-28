package com.architectureclean.recipes.ui.screens.recipes.resultComposables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kqm.architectureclean.domain.Ingredient
import com.kqm.architectureclean.domain.IngredientMeasure
import com.kqm.architectureclean.domain.Recipe

class SuccessScreenshotPreview {
    @Preview(showBackground = true)
    @Composable
    fun SuccessScreenPreview() {
        val recipe = listOf(
            Recipe(
                id = "1",
                name = "Recipe 1",
                imageThumb = "https://www.themealdb.com/images/media/meals/st1ifa1583267248.jpg",
                videoThumb = "",
                area = "Area 1",
                category = "Category 1",
                instructions = "Instructions 1",
                ingredientMeasures = listOf(
                    IngredientMeasure(Ingredient("Ingredient 1"), "Measure 1"),
                    IngredientMeasure(Ingredient("Ingredient 2"), "Measure 2"),
                    IngredientMeasure(Ingredient("Ingredient 3"), "Measure 3")
                ),
                favorite = false
            ), Recipe(
                id = "2",
                name = "Recipe 2",
                imageThumb = "https://www.themealdb.com/images/media/meals/st1ifa1583267248.jpg",
                videoThumb = "",
                area = "Area 2",
                category = "Category 2",
                instructions = "Instructions 2",
                ingredientMeasures = listOf(
                    IngredientMeasure(Ingredient("Ingredient 1"), "Measure 1"),
                    IngredientMeasure(Ingredient("Ingredient 2"), "Measure 2"),
                    IngredientMeasure(Ingredient("Ingredient 3"), "Measure 3")
                ),
                favorite = false
            ), Recipe(
                id = "3",
                name = "Recipe 3",
                imageThumb = "https://www.themealdb.com/images/media/meals/st1ifa1583267248.jpg",
                videoThumb = "",
                area = "Area 3",
                category = "Category 3",
                instructions = "Instructions 3",
                ingredientMeasures = listOf(
                    IngredientMeasure(Ingredient("Ingredient 1"), "Measure 1"),
                    IngredientMeasure(Ingredient("Ingredient 2"), "Measure 2"),
                    IngredientMeasure(Ingredient("Ingredient 3"), "Measure 3")
                ),
                favorite = false
            )
        )
        val state = com.kqm.architectureclean.presentation.ResultCall.Success(recipe)
        com.kqm.architectureclean.presentation.ui.screens.recipes.resultComposables.SuccessScreen(
            PaddingValues(),
            state
        ) {}
    }
}