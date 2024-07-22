package com.architectureclean.recipes

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RecipesApplication: Application()

//    lateinit var db: RoomRecipesDatabase
//       private set
//
//    override fun onCreate() {
//        super.onCreate()
//        db = Room.databaseBuilder(applicationContext, RoomRecipesDatabase::class.java, "recipes-db")
//            .build()
//    }


