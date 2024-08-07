package com.kqm.architectureclean.data

import com.kqm.architectureclean.domain.Recipe
import com.kqm.architectureclean.test.unit.helpers.generateRecipes
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class RecipesRepositoryTest {

    @Mock
    lateinit var localDataSource: RecipesLocalDataSourceImpl

    @Mock
    lateinit var remoteDataSource: RecipesRemoteDataSourceImpl


    private lateinit var recipesRepository: RecipesRepository

    private val testRecipes = generateRecipes(5)


    @Before
    fun setUp() {
        recipesRepository = RecipesRepository(remoteDataSource, localDataSource)
    }

    @Test
    fun `when local data source contains data then return it`(): Unit = runBlocking {

        whenever(localDataSource.localRecipes).thenReturn(flowOf(testRecipes))

        val result = recipesRepository.recipes.first()

        assertEquals(testRecipes, result)
    }

    @Test
    fun `when local data source does not contain data then return remote data source`(): Unit =
        runBlocking {
            val listEmpty = emptyList<Recipe>()
            whenever(localDataSource.localRecipes).thenReturn(flowOf(listEmpty))
            whenever(remoteDataSource.getRecipe()).thenReturn(testRecipes[0])

            recipesRepository.recipes.first()

            verify(remoteDataSource).getRecipe()
        }

    @Test
    fun `adding recipe calls remote data source and saves in local data source`(): Unit =
        runBlocking {
            val newRecipe = generateRecipes(6)[5]

            whenever(remoteDataSource.getRecipe()).thenReturn(newRecipe)
            whenever(localDataSource.localRecipes).thenReturn(flowOf(testRecipes + newRecipe))

            recipesRepository.addRecipe()
            val result = recipesRepository.recipes.first()

            verify(remoteDataSource).getRecipe()
            verify(localDataSource).insertRecipe(newRecipe)

            val expected = testRecipes + newRecipe
            assertEquals(expected, result)
        }

    @Test
    fun `deleteRecipe should call delete in local data source and update recipes`(): Unit =
        runBlocking {
            val deleteRecipe = testRecipes[0]
            whenever(localDataSource.localRecipes).thenReturn(flowOf(testRecipes - deleteRecipe))

            recipesRepository.deleteRecipe(deleteRecipe)
            verify(localDataSource).deleteRecipe(deleteRecipe)

            val result = recipesRepository.recipes.first()
            assertEquals(testRecipes - deleteRecipe, result)
        }

    @Test
    fun `updateFavorite should call insertRecipe in local data source and update value favorite`(): Unit =
        runBlocking {
            val newsRecipe = listOf(testRecipes[0].copy(favorite = true))
            whenever(localDataSource.localRecipes).thenReturn(flowOf(newsRecipe))

            recipesRepository.updateFavorite(testRecipes[0])

            verify(localDataSource).insertRecipe(newsRecipe[0])
            val result = recipesRepository.recipes.first()[0].favorite

            assertTrue(result)
        }
}