package com.kkm.recetas.repository

import com.kkm.recetas.data.local.database.RecipesDao
import com.kkm.recetas.data.local.model.Recipe
import com.kkm.recetas.extensions.toEntity
import com.kkm.recetas.extensions.toRecipe2
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecipesLocalDataSource(private val recipesDao: RecipesDao) {

    val localRecipes: Flow<List<Recipe>> = recipesDao.getLocalRecipes().map { it.map { entity -> entity.toRecipe2() } }

    suspend fun insertRecipe(recipe: Recipe) {
        recipesDao.insertRecipe(recipe.toEntity())
    }

    suspend fun deleteRecipe(recipe: Recipe) {
        recipesDao.deleteRecipe(recipe.toEntity())
    }
}