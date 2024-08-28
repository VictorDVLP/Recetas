package com.kqm.architectureclean.test.unit.fakes

import com.kqm.architectureclean.data.RecipesLocalDataSourceImpl
import com.kqm.architectureclean.domain.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class LocalDataSourceFake : RecipesLocalDataSourceImpl {

    var inMemoryTest = MutableStateFlow<List<Recipe>>(emptyList())

    override val localRecipes: Flow<List<Recipe>> = inMemoryTest

    override suspend fun insertRecipe(recipe: Recipe) {
        inMemoryTest.value = inMemoryTest.value.toMutableList().apply {
            if (none { it.id == recipe.id }) add(recipe)
        }
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        inMemoryTest.value -= recipe
    }
}