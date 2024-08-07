package com.architectureclean.recipes.ui.viewmodel

import app.cash.turbine.test
import com.architectureclean.recipes.ResultCall
import com.architectureclean.recipes.framework.RecipesLocalDataSource
import com.architectureclean.recipes.rule.MockWebServerRule
import com.architectureclean.recipes.ui.viewmodel.RecipesViewModel
import com.kqm.architectureclean.domain.Recipe
import com.kqm.architectureclean.test.unit.helpers.generateRecipes
import com.kqm.architectureclean.usecases.AddRecipeUseCase
import com.kqm.architectureclean.usecases.DeleteRecipeUseCase
import com.kqm.architectureclean.usecases.GetAllRecipesUseCase
import com.kqm.architectureclean.usecases.UpdateFavoriteUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class IntegrationTestViewModel {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val mockWebServerRule = MockWebServerRule()


    @Inject
    lateinit var localDataSource: RecipesLocalDataSource

    @Inject
    lateinit var getAllRecipesUseCase: GetAllRecipesUseCase

    @Inject
    lateinit var addRecipeUseCase: AddRecipeUseCase

    @Inject
    lateinit var updateFavoriteUseCase: UpdateFavoriteUseCase

    @Inject
    lateinit var deleteRecipeUseCase: DeleteRecipeUseCase


    private lateinit var viewModel: RecipesViewModel

    @Before
    fun setUp() {
        hiltRule.inject()
        viewModel = RecipesViewModel(
            getAllRecipesUseCase,
            addRecipeUseCase,
            updateFavoriteUseCase,
            deleteRecipeUseCase
        )
    }

    @Test
    fun whenLocalDataSourceIsEmpty_CallRemoteDataSource_AndSaveInLocal(): Unit = runTest {
        viewModel.state.test {
            assertEquals(ResultCall.Loading, awaitItem())
            assertEquals(ResultCall.Success(emptyList<Recipe>()), awaitItem())
            assertEquals(
                "Crispy Sausages and Greens",
                (awaitItem() as ResultCall.Success).data[0].name
            )
        }
    }

    @Test
    fun whenLocalDataSourceIsNotEmpty_ReturnLocalDataSource(): Unit = runTest {
        val recipeTest = generateRecipes(1)[0]
        localDataSource.insertRecipe(recipeTest)

        viewModel.state.test {
            assertEquals(ResultCall.Loading, awaitItem())
            val success = awaitItem() as ResultCall.Success
            assertEquals("Recipe 1", success.data[0].name)
        }
    }

    @Test
    fun whenAddRecipeIsCalled_AddRecipeInLocalDataSource(): Unit = runTest {
        localDataSource.insertRecipe(generateRecipes(1)[0])

        viewModel.state.test {
            assertEquals(ResultCall.Loading, awaitItem())
            assertEquals(ResultCall.Success(generateRecipes(1)), awaitItem())

            viewModel.addRecipe()
             val success = awaitItem() as ResultCall.Success

            assertEquals(2, success.data.size)
            assertEquals("Crispy Sausages and Greens", success.data[1].name)
        }
    }

    @Test
    fun whenDeleteRecipeIsCalled_DeleteRecipeInLocalDataSource(): Unit = runTest {
        localDataSource.insertRecipe(generateRecipes(1)[0])
        localDataSource.insertRecipe(generateRecipes(2)[1])

        viewModel.state.test {
            assertEquals(ResultCall.Loading, awaitItem())
            assertEquals(ResultCall.Success(generateRecipes(2)), awaitItem())

            viewModel.deleteRecipe(generateRecipes(2)[1])
            assertEquals(generateRecipes(1), (awaitItem() as ResultCall.Success).data)
        }
    }

    @Test
    fun whenUpdateFavoriteIsCalled_UpdateFavoriteInLocalDataSource(): Unit = runTest {
        localDataSource.insertRecipe(generateRecipes(1)[0])
        localDataSource.insertRecipe(generateRecipes(2)[1])

        viewModel.state.test {
            assertEquals(ResultCall.Loading, awaitItem())
            assertEquals(ResultCall.Success(generateRecipes(2)), awaitItem())
            viewModel.updateFavorite(generateRecipes(2)[1])
            assertEquals(true, (awaitItem() as ResultCall.Success).data[1].favorite)
        }
    }
}
