package com.kkm.recetas.repository

import com.kkm.recetas.data.local.database.RecipesDao
import com.kkm.recetas.domain.Recipe
import com.kkm.recetas.extensions.toEntity
import com.kkm.recetas.extensions.toRecipe2
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface RecipesLocalDataSourceImpl {
    val localRecipes: Flow<List<Recipe>>

    suspend fun insertRecipe(recipe: List<Recipe>)

    suspend fun deleteRecipe(recipe: Recipe)
}

class RecipesLocalDataSource(private val recipesDao: RecipesDao) : RecipesLocalDataSourceImpl {

    override val localRecipes: Flow<List<Recipe>> = recipesDao.getLocalRecipes().map { it.map { entity -> entity.toRecipe2() } }

    override suspend fun insertRecipe(recipe: List<Recipe>) {
        recipesDao.insertRecipe(recipe[0].toEntity())
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        recipesDao.deleteRecipe(recipe.toEntity())
    }
}