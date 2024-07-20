package com.kqm.architectureclean.usecases

import com.kqm.architectureclean.data.RecipesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class AddRecipeUseCaseTest {

    @Test
    fun `invoke call method added to repository`(): Unit = runBlocking {

        val repository = mock<RecipesRepository> {
            onBlocking { addRecipe() }.thenReturn(Unit)
        }

        AddRecipeUseCase( repository ).invoke()

        verify(repository, times(1)).addRecipe()
    }
}