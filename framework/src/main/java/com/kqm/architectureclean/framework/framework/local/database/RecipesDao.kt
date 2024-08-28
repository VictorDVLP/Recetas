package com.kqm.architectureclean.framework.framework.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.kqm.architectureclean.domain.Recipe
import com.kqm.architectureclean.framework.framework.toLocalIngredient
import com.kqm.architectureclean.framework.framework.toLocalMeasure
import com.kqm.architectureclean.framework.framework.toRecipeWithIngredientsAndMeasures
import kotlinx.coroutines.flow.Flow
import com.kqm.architectureclean.framework.framework.local.database.LocalRecipe as LocalRecipe

@Dao
interface RecipesDao {
    @Transaction
    @Query("SELECT * FROM recipes")
    fun getLocalRecipes(): Flow<List<RecipeWithIngredientsAndMeasures>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocalRecipe(recipe: LocalRecipe)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocalIngredients(ingredient: List<LocalIngredient>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocalMeasures(measure: List<LocalMeasure>)

    @Transaction
    suspend fun insertRecipe(recipe: RecipeWithIngredientsAndMeasures) {
        insertLocalRecipe(recipe.recipe)
        insertLocalIngredients(recipe.ingredients)
        insertLocalMeasures(recipe.measures)
    }

    @Delete
    suspend fun deleteRecipe(recipe: LocalRecipe)

}