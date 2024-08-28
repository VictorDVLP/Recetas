package com.kqm.architectureclean.framework.framework.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LocalRecipe::class, LocalIngredient::class, LocalMeasure::class],
    version = 1,
    exportSchema = false
)
abstract class RoomRecipesDatabase : RoomDatabase() {
    abstract fun recipesDao(): RecipesDao
}