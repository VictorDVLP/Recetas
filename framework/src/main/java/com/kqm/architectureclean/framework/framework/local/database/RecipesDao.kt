package com.kqm.architectureclean.framework.framework.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.kqm.architectureclean.framework.framework.local.database.LocalRecipes as LocalRecipe

@Dao
interface RecipesDao {
    @Query("SELECT * FROM recipes")
    fun getLocalRecipes(): Flow<List<LocalRecipe>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: LocalRecipe)

    @Delete
    suspend fun deleteRecipe(recipe: LocalRecipe)

}