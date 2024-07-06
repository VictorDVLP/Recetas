package com.kqm.architectureclean.data

import com.kqm.architectureclean.domain.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class RecipesRepository @Inject constructor (
    private val recipesRemoteDataSource: RecipesRemoteDataSourceImpl,
    private val recipesLocalDataSource: RecipesLocalDataSourceImpl
) {
    val recipes: Flow<List<Recipe>> =
        recipesLocalDataSource.localRecipes.transform { localRecipes ->
            if (localRecipes.isEmpty()) {
                recipesRemoteDataSource.getRecipe().also { remoteRecipes ->
                    recipesLocalDataSource.insertRecipe(remoteRecipes)
                }
            }
            emit(localRecipes)
        }

    suspend fun addRecipe() {
        val request = recipesRemoteDataSource.getRecipe()
        recipesLocalDataSource.insertRecipe(request)
    }

    suspend fun updateFavorite(recipe: Recipe) {
        recipesLocalDataSource.insertRecipe(listOf(recipe.copy(favorite = !recipe.favorite)))
    }

    suspend fun deleteRecipe(recipe: Recipe) {
        recipesLocalDataSource.deleteRecipe(recipe)
    }
}
