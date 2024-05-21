package com.kkm.recetas.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.kkm.recetas.repository.RecipesRepository
import com.kkm.recetas.ui.screens.DetailScreen
import com.kkm.recetas.ui.screens.RecipesScreen


@Composable
fun NavigationRecipes(repository: RecipesRepository) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Home) {
        composable<Home> {
            RecipesScreen( repository = repository ) { navController.navigate(RecipeDetail(it)) }
        }

        composable<RecipeDetail> { backStackEntry ->
            val detail = backStackEntry.toRoute<RecipeDetail>()
            DetailScreen( id = detail.id, repository = repository )
            { navController.popBackStack() }
        }
    }
}
