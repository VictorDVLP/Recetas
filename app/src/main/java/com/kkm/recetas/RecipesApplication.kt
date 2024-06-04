package com.kkm.recetas

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.room.Room
import com.kkm.recetas.data.local.database.RoomRecipesDatabase
import com.kkm.recetas.navigation.NavigationRecipes
import com.kkm.recetas.repository.RecipesRepository


class RecipesApplication: Application() {

    lateinit var db: RoomRecipesDatabase
       private set

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext, RoomRecipesDatabase::class.java, "recipes-db")
            .build()
    }

}
