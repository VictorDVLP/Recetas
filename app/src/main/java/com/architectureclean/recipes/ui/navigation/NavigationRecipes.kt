package com.architectureclean.recipes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.architectureclean.recipes.ui.screens.detail.DetailScreen
import com.architectureclean.recipes.ui.screens.recipes.RecipesScreen


@Composable
fun NavigationRecipes() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Home) {
        composable<Home> {
            RecipesScreen(onNavigateDetail = {
                navController.navigate(
                    RecipeDetail(it)
                )
            }, onBackNavigate = { navController.navigate(Home) })
        }

        composable<RecipeDetail> { backStackEntry ->
            val detail = backStackEntry.toRoute<RecipeDetail>()
            DetailScreen(id = detail.id)
            { navController.popBackStack() }
        }
    }
}
