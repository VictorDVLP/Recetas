package com.kqm.architectureclean.framework.framework

import com.kqm.architectureclean.data.RecipesLocalDataSourceImpl
import com.kqm.architectureclean.domain.Recipe
import com.kqm.architectureclean.framework.framework.local.database.RecipesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class RecipesLocalDataSource @Inject constructor(private val recipesDao: RecipesDao) : RecipesLocalDataSourceImpl {

    override val localRecipes: Flow<List<Recipe>> = recipesDao.getLocalRecipes().map { it.map { entity -> entity.toRecipe2() } }

    override suspend fun insertRecipe(recipe: Recipe) {
        recipesDao.insertRecipe(recipe.toRecipeWithIngredientsAndMeasures())
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        recipesDao.deleteRecipe(recipe.toRecipeWithIngredientsAndMeasures().recipe)
    }
}