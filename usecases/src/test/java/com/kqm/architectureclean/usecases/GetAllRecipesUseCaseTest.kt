package com.kqm.architectureclean.usecases

import com.kqm.architectureclean.domain.Recipe
import com.kqm.architectureclean.test.unit.helpers.generateRecipes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock

class GetAllRecipesUseCaseTest {

    @Test
    fun `invoke call variable recipes from repository`() {
        val recipesFlow: Flow<List<Recipe>> = flowOf(generateRecipes(5))

        val useCase = GetAllRecipesUseCase(mock { on { recipes }.thenReturn(recipesFlow) })

        assertEquals(recipesFlow, useCase())
    }
}