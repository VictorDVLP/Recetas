package com.kkm.recetas.repository

import com.kkm.recetas.data.local.model.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.transform

class RecipesRepository(
    private val recipesRemoteDataSource: RecipesRemoteDataSource,
    private val recipesLocalDataSource: RecipesLocalDataSource
) {
    val recipes: Flow<List<Recipe>> =
        recipesLocalDataSource.localRecipes.transform { localRecipes ->
            val recipes: List<Recipe> = localRecipes.takeIf { it.isNotEmpty() }
                ?: recipesRemoteDataSource.getRecipe().also {
                        recipesLocalDataSource.insertRecipe(it)
                    }
            emit(recipes)
        }

    suspend fun addRecipe() {
            val request = recipesRemoteDataSource.getRecipe()
            recipesLocalDataSource.insertRecipe(request)
    }

        suspend fun deleteRecipe(recipe: Recipe) {
            recipesLocalDataSource.deleteRecipe(recipe)
        }
    }
