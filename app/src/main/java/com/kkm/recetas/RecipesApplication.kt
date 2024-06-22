package com.kkm.recetas

import android.app.Application
import androidx.room.Room
import com.kkm.recetas.framework.local.database.RoomRecipesDatabase


class RecipesApplication: Application() {

    lateinit var db: RoomRecipesDatabase
       private set

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext, RoomRecipesDatabase::class.java, "recipes-db")
            .build()
    }

}
