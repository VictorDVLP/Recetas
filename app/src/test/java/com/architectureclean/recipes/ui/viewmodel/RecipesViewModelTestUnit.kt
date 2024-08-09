package com.architectureclean.recipes.ui.viewmodel

import app.cash.turbine.test
import com.kqm.architectureclean.presentation.ResultCall
import com.architectureclean.testRules.CoroutinesTestRule
import com.kqm.architectureclean.presentation.ui.viewmodel.RecipesViewModel
import com.kqm.architectureclean.test.unit.helpers.generateRecipes
import com.kqm.architectureclean.usecases.AddRecipeUseCase
import com.kqm.architectureclean.usecases.DeleteRecipeUseCase
import com.kqm.architectureclean.usecases.GetAllRecipesUseCase
import com.kqm.architectureclean.usecases.UpdateFavoriteUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class RecipesViewModelTestUnit {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()


    @Mock
    lateinit var getRecipesUseCase: GetAllRecipesUseCase

    @Mock
    lateinit var addRecipeUseCase: AddRecipeUseCase

    @Mock
    lateinit var deleteRecipeUseCase: DeleteRecipeUseCase

    @Mock
    lateinit var updateFavoriteUseCase: UpdateFavoriteUseCase


    private lateinit var viewModel: RecipesViewModel


    
    @Test
    fun `when state is success then state should be list of recipes`(): Unit = runTest {

        val recipes = generateRecipes(5)
        whenever(getRecipesUseCase()).thenReturn(flowOf(recipes))
        viewModel = RecipesViewModel(
            getRecipesUseCase,
            addRecipeUseCase,
            updateFavoriteUseCase,
            deleteRecipeUseCase
        )

        viewModel.state.test {
            assertEquals(ResultCall.Loading, awaitItem())
            assertEquals(ResultCall.Success(recipes), awaitItem())
        }

    }


    @Test
    fun `when state is error then state should be error`(): Unit = runTest {
        val exception = IOException("Fatal error")
        whenever(getRecipesUseCase()).thenReturn(flow { throw exception })
        viewModel = RecipesViewModel(
            getRecipesUseCase,
            addRecipeUseCase,
            updateFavoriteUseCase,
            deleteRecipeUseCase
        )

        viewModel.state.test {
            assertEquals(ResultCall.Loading, awaitItem())
            val exceptionMessage = (awaitItem() as ResultCall.Error).exception.message
            assertEquals("Network error", exceptionMessage)
        }
    }

    @Test
    fun `add recipe should call addRecipeUseCase`() = runTest {

        val recipes = generateRecipes(5)
        whenever(getRecipesUseCase()).thenReturn(flowOf(recipes))
        viewModel = RecipesViewModel(
            getRecipesUseCase,
            addRecipeUseCase,
            updateFavoriteUseCase,
            deleteRecipeUseCase
        )

        viewModel.addRecipe()

        runCurrent()

        verify(addRecipeUseCase, times(1)).invoke()

    }


    @Test
    fun `update favorite should call updateFavoriteUseCase`(): Unit = runTest {

        val recipes = generateRecipes(5)
        whenever(getRecipesUseCase()).thenReturn(flowOf(recipes))
        viewModel = RecipesViewModel(
            getRecipesUseCase,
            addRecipeUseCase,
            updateFavoriteUseCase,
            deleteRecipeUseCase
        )

        val favoriteRecipe = generateRecipes(5)[4]

        viewModel.updateFavorite(favoriteRecipe)

        runCurrent()

        verify(updateFavoriteUseCase, times(1)).invoke(favoriteRecipe)
    }


    @Test
    fun `delete recipe should call deleteRecipeUseCase`(): Unit = runTest {

        val initRecipes = generateRecipes(5)
        whenever(getRecipesUseCase()).thenReturn(flowOf(initRecipes))
        viewModel = RecipesViewModel(
            getRecipesUseCase,
            addRecipeUseCase,
            updateFavoriteUseCase,
            deleteRecipeUseCase
        )

        val deleteRecipe = generateRecipes(5)[4]

        viewModel.deleteRecipe(deleteRecipe)

        runCurrent()

        verify(deleteRecipeUseCase, times(1)).invoke(deleteRecipe)
    }
}