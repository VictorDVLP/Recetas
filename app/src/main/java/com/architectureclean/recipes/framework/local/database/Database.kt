package com.architectureclean.recipes.framework.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalRecipes::class], version = 1, exportSchema = false)
abstract class RoomRecipesDatabase : RoomDatabase() {
    abstract fun recipesDao(): RecipesDao
}