package com.kkm.recetas

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.room.Room.databaseBuilder
import com.kkm.recetas.data.local.database.RoomRecipesDatabase
import com.kkm.recetas.repository.RecipesLocalDataSource
import com.kkm.recetas.repository.RecipesRemoteDataSource
import com.kkm.recetas.repository.RecipesRepository
import com.kkm.recetas.ui.theme.RecipesTheme

class MainActivity : ComponentActivity() {
    private lateinit var db: RoomRecipesDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = databaseBuilder(applicationContext, RoomRecipesDatabase::class.java, "recipes-db")
            .build()

        val repository = RecipesRepository(
           recipesRemoteDataSource = RecipesRemoteDataSource(),
            recipesLocalDataSource = RecipesLocalDataSource(db.recipesDao())
        )

        setContent {
            RecipesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RecipesApplication(repository)
                    Log.d("TAG", "onCreate: ${repository.recipes}")
                }
            }
        }
    }
}