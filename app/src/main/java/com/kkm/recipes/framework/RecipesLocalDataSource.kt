package com.kkm.recipes.framework

import com.kkm.recipes.framework.local.database.RecipesDao
import com.kqm.architectureclean.data.RecipesLocalDataSourceImpl
import com.kqm.architectureclean.domain.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


internal class RecipesLocalDataSource @Inject constructor(private val recipesDao: RecipesDao) : RecipesLocalDataSourceImpl {

    override val localRecipes: Flow<List<Recipe>> = recipesDao.getLocalRecipes().map { it.map { entity -> entity.toRecipe2() } }

    override suspend fun insertRecipe(recipe: List<Recipe>) {
        recipesDao.insertRecipe(recipe[0].toEntity())
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        recipesDao.deleteRecipe(recipe.toEntity())
    }
}