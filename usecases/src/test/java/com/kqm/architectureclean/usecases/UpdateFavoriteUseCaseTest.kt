package com.kqm.architectureclean.usecases

import com.kqm.architectureclean.data.RecipesRepository
import com.kqm.architectureclean.test.unit.helpers.generateRecipes
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify


class UpdateFavoriteUseCaseTest {

    @Test
    fun `invoke call method updateFavorite in repository`() = runBlocking {
        val recipe = generateRecipes(3)
        val repository = mock<RecipesRepository> {
            onBlocking { updateFavorite(recipe[1]) }.thenReturn(Unit)
        }

        UpdateFavoriteUseCase(repository).invoke(recipe[1])

        verify(repository).updateFavorite(recipe[1])
    }
}