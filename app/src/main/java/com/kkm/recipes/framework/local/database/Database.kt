package com.kkm.recipes.framework.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalRecipes::class], version = 1, exportSchema = false)
internal abstract class RoomRecipesDatabase: RoomDatabase() {
    abstract fun recipesDao(): RecipesDao
}