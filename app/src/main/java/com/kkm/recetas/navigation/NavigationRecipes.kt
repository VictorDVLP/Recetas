package com.kkm.recetas.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kkm.recetas.repository.RecipesRemoteDataSource
import com.kkm.recetas.repository.RecipesRepository
import com.kkm.recetas.ui.screens.RecipesScreen


@Composable
fun NavigationRecipes() {
    val navController = rememberNavController()
    val remoteDataSource = RecipesRemoteDataSource()
    val repository = RecipesRepository(recipesRemoteDataSource = remoteDataSource)

    NavHost(navController = navController, startDestination = "recipes") {
        composable("recipes") {
            RecipesScreen( repository = repository )
        }
        
    }
}
