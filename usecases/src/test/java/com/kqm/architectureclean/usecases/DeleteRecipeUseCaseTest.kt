package com.kqm.architectureclean.usecases

import com.kqm.architectureclean.data.RecipesRepository
import com.kqm.architectureclean.usecases.helpers.generateRecipes
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify


class DeleteRecipeUseCaseTest {

    @Test
    fun `invoke call method deleted to repository`() = runBlocking {
        val recipe = generateRecipes(6).first()
        val repository = mock<RecipesRepository> {
            onBlocking { deleteRecipe(recipe) }.thenReturn(Unit)
        }

        DeleteRecipeUseCase(repository).invoke(recipe)

        verify(repository).deleteRecipe(recipe)
    }
}