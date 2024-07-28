package com.kqm.architectureclean.test.unit.fakes

import com.kqm.architectureclean.data.RecipesLocalDataSourceImpl
import com.kqm.architectureclean.domain.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class LocalDataSourceFake(localData: List<Recipe>): RecipesLocalDataSourceImpl {

    private var inMemoryTest: List<Recipe> = localData

    override val localRecipes: Flow<List<Recipe>> = flowOf(inMemoryTest)

    override suspend fun insertRecipe(recipe: List<Recipe>) {
        val result = inMemoryTest + recipe
        inMemoryTest = result
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        val result = inMemoryTest - recipe
        inMemoryTest = result
    }
}