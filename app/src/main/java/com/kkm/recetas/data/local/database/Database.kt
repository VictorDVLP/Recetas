package com.kkm.recetas.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalRecipes::class], version = 1)
abstract class RoomRecipesDatabase: RoomDatabase() {
    abstract fun recipesDao(): RecipesDao
}