package com.kkm.recetas.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kkm.recetas.repository.RecipesRepository
import com.kkm.recetas.ui.screens.DetailScreen
import com.kkm.recetas.ui.screens.RecipesScreen


@Composable
fun NavigationRecipes(repository: RecipesRepository) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "recipes") {
        composable("recipes") {
            RecipesScreen( repository = repository ) { navController.navigate("detail/$it") }
        }

        composable("detail/{id}") {
            val id = it.arguments?.getString("id") ?: ""
            DetailScreen( id = id, repository = repository ) { navController.popBackStack() }
        }
    }
}
