package com.architectureclean.recipes.ui.screens

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.architectureclean.recipes.ResultCall
import com.architectureclean.recipes.ui.screens.recipes.RecipesScreen
import com.architectureclean.recipes.ui.screens.recipes.resultComposables.BUTTON_RETRY_TEST_TAG
import com.architectureclean.recipes.ui.screens.recipes.resultComposables.LOADING_INDICATOR_TEST_TAG
import com.kqm.architectureclean.test.unit.helpers.generateRecipes
import okio.IOException
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class RecipesScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenStateIsSuccess_shouldShowRecipes(): Unit = with(composeTestRule) {
        val state = ResultCall.Success(generateRecipes(5))

        setContent {
            RecipesScreen(state = state, addRecipe = {}, onNavigateDetail = {}, onBackNavigate = {})
        }

        onNodeWithText("Recipe 3").isDisplayed()
    }

    @Test
    fun whenClickOnRecipe_isCalledNavigateDetail(): Unit = with(composeTestRule) {
        var idRecipe = ""
        val state = ResultCall.Success(generateRecipes(5))

        setContent {
            RecipesScreen(state = state, addRecipe = {}, onNavigateDetail = {
                idRecipe = it
            }, onBackNavigate = {})
        }

        onNodeWithText("Recipe 3").performClick()
        assertEquals("3", idRecipe)
    }


    @Test
    fun whenStateIsLoading_shouldLoadingIndicator(): Unit = with(composeTestRule) {
        val state = ResultCall.Loading

        setContent {
            RecipesScreen(state = state, addRecipe = {}, onNavigateDetail = {}, onBackNavigate = {})
        }

        onNodeWithTag(LOADING_INDICATOR_TEST_TAG).isDisplayed()

    }

    @Test
    fun whenStateIsError_showMessageError(): Unit = with(composeTestRule) {
        val state = ResultCall.Error(IOException("Network error"))

        setContent {
            RecipesScreen(state = state, addRecipe = {}, onNavigateDetail = {}, onBackNavigate = {})
        }

        onNodeWithText("Network error").isDisplayed()
    }


    @Test
    fun whenPulseButtonRetryIsClicked_shouldLoadingIndicator(): Unit = with(composeTestRule) {
        val state = ResultCall.Error(IOException("Network error"))

        setContent {
            RecipesScreen(state = state, addRecipe = {}, onNavigateDetail = {}, onBackNavigate = {})
        }

        onNodeWithTag(BUTTON_RETRY_TEST_TAG).performClick()
        onNodeWithTag(LOADING_INDICATOR_TEST_TAG).isDisplayed()
    }
}