package com.architectureclean.recipes.ui.viewmodel

import app.cash.turbine.test
import com.kqm.architectureclean.presentation.ResultCall
import com.architectureclean.testRules.CoroutinesTestRule
import com.kqm.architectureclean.domain.Recipe
import com.kqm.architectureclean.test.unit.helpers.generateRecipes
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class IntegrationTestViewModelFakes {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `when local data source is empty, call remote data source and save in local data source`(): Unit =
        runTest {
            val vm = buildViewModelFake()
            val result = generateRecipes(5)

            vm.state.test {
                assertEquals(ResultCall.Loading, awaitItem())
                assertEquals(ResultCall.Success(emptyList<Recipe>()), awaitItem())
                assertEquals(ResultCall.Success(result), awaitItem())
            }
        }

    @Test
    fun `when local data source is not empty, return local data source`(): Unit =
        runTest {
            val localRecipe = generateRecipes(5)
            val vm = buildViewModelFake(localData = localRecipe)

            vm.state.test {
                assertEquals(ResultCall.Loading, awaitItem())
                assertEquals(ResultCall.Success(localRecipe), awaitItem())
            }
        }

    @Test
    fun `when add recipe is called, add recipe in local data source`(): Unit =
        runTest {
            val localRecipes = generateRecipes(1)
            val vm = buildViewModelFake(localData = localRecipes)

            vm.addRecipe()
            val result = generateRecipes(5)

            vm.state.test {
                assertEquals(ResultCall.Loading, awaitItem())
                assertEquals(ResultCall.Success(result), awaitItem())
            }
        }

    @Test
    fun `when delete recipe is called, delete recipe in local data source`(): Unit =
        runTest {
            val localRecipes = generateRecipes(5)
            val vm = buildViewModelFake(localData = localRecipes)
            val deleteRecipe = localRecipes.last()

            vm.deleteRecipe(deleteRecipe)
            val result = localRecipes - deleteRecipe

            vm.state.test {
                assertEquals(ResultCall.Loading, awaitItem())
                assertEquals(ResultCall.Success(result), awaitItem())
            }
        }

    @Test
    fun `when update recipe favorite is called, update favorite in local data source`(): Unit =
        runTest {
            val localRecipes = generateRecipes(1)
            val vm = buildViewModelFake(localData = localRecipes)

            vm.updateFavorite(localRecipes.first())

            val result = listOf(localRecipes.first().copy(favorite = true))

            vm.state.test {
                assertEquals(ResultCall.Loading, awaitItem())
                assertEquals(ResultCall.Success(result), awaitItem())
            }
        }
}