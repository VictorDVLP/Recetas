package com.kkm.recetas.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {
    @Query("SELECT * FROM recipes")
    fun getRecipes(): Flow<List<LocalRecipes>>

    @Insert
    suspend fun insertRecipe(recipe: LocalRecipes)

    @Delete
    suspend fun deleteRecipe(recipe: LocalRecipes)

}