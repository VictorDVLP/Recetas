package com.kqm.architectureclean.test.unit.fakes

import com.kqm.architectureclean.data.RecipesLocalDataSourceImpl
import com.kqm.architectureclean.domain.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class LocalDataSourceFake : RecipesLocalDataSourceImpl {

    var inMemoryTest = MutableStateFlow<List<Recipe>>(emptyList())

    override val localRecipes: Flow<List<Recipe>> = inMemoryTest

    override suspend fun insertRecipe(recipe: List<Recipe>) {
        inMemoryTest.value = inMemoryTest.value.toMutableList().apply {
            recipe.forEach { newRecipe ->
                removeAll { it.id == newRecipe.id }
                add(newRecipe)
            }
        }
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        inMemoryTest.value -= recipe
    }
}