package com.architectureclean.recipes.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.architectureclean.recipes.ui.screens.detail.DetailScreen
import com.architectureclean.recipes.ui.screens.detail.DetailStateImpl
import com.kqm.architectureclean.test.unit.helpers.generateRecipes
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var detailState: DetailStateImpl

    @Before
    fun setUp() {
        detailState = DetailStateFake()
    }

    @Test
    fun whenStateIsSuccess_shouldShowRecipe(): Unit = with(composeTestRule) {
        val recipeTest = generateRecipes(1)[0]

        setContent {
            DetailScreen( recipe = recipeTest, detailState = detailState, onDeleteClick = {}, onFavoriteClick = {}, onBack = {})
        }

        onNodeWithText(recipeTest.name).assertIsDisplayed()
        onNodeWithText(recipeTest.instructions).assertIsDisplayed()
    }

    @Test
    fun whenClickInFavorite_callOnFavoriteClick(): Unit = with(composeTestRule) {
        var isFavorite = false
        val recipeTest = generateRecipes(1)[0]

        setContent {
            DetailScreen( recipe = recipeTest, detailState = detailState, onDeleteClick = {}, onFavoriteClick = {
                isFavorite = !isFavorite
            }, onBack = {})
        }

        onNodeWithText(recipeTest.name).assertIsDisplayed()
        onNodeWithContentDescription("favorite").assertIsDisplayed()
        onNodeWithContentDescription("favorite").performClick()

        assertTrue(isFavorite)
    }

    @Test
    fun whenClickInDelete_callOnDeleteClick(): Unit = with(composeTestRule) {
        var isDelete = false
        val recipeTest = generateRecipes(1)[0]

        setContent {
            DetailScreen( recipe = recipeTest, detailState = detailState, onDeleteClick = {
                isDelete = true
            }, onFavoriteClick = {}, onBack = {})
        }

        onNodeWithText(recipeTest.name).assertIsDisplayed()
        onNodeWithContentDescription("Delete").assertIsDisplayed()
        onNodeWithContentDescription("Delete").performClick()

        assertTrue(isDelete)
    }

    @Test
    fun whenClickOnBack_callOnBack(): Unit = with(composeTestRule) {
        var isBack = false
        val recipeTest = generateRecipes(1)[0]

        setContent {
            DetailScreen( recipe = recipeTest, detailState = detailState, onDeleteClick = {}, onFavoriteClick = {}, onBack = {
                isBack = true
            })
        }

        onNodeWithText(recipeTest.name).assertIsDisplayed()
        onNodeWithContentDescription("Back").assertIsDisplayed()
        onNodeWithContentDescription("Back").performClick()

        assertTrue(isBack)
    }

    @Test
    fun whenClickInShare_callOnShareClick(): Unit = with(composeTestRule) {
        val recipeTest = generateRecipes(1)[0]
        val detailState = DetailStateFake()

        setContent {
            DetailScreen( recipe = recipeTest, detailState = detailState, onDeleteClick = {}, onFavoriteClick = {}, onBack = {} )
        }

        onNodeWithContentDescription("Share").assertIsDisplayed()
        onNodeWithContentDescription("Share").performClick()

        assertEquals(recipeTest, detailState.recipeShared)
    }
}