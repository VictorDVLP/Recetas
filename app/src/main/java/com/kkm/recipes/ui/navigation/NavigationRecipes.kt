package com.kkm.recipes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.kkm.recipes.RecipesApplication
import com.kqm.architectureclean.data.RecipesRepository
import com.kkm.recipes.framework.RecipesLocalDataSource
import com.kkm.recipes.framework.RecipesRemoteDataSource
import com.kkm.recipes.framework.remote.RecipesApi
import com.kkm.recipes.ui.screens.detail.DetailScreen
import com.kkm.recipes.ui.screens.recipes.RecipesScreen
import com.kkm.recipes.ui.viewmodel.RecipesViewModel
import com.kqm.architectureclean.usecases.AddRecipeUseCase
import com.kqm.architectureclean.usecases.DeleteRecipeUseCase
import com.kqm.architectureclean.usecases.GetAllRecipesUseCase
import com.kqm.architectureclean.usecases.UpdateFavoriteUseCase


@Composable
fun NavigationRecipes() {
    val navController = rememberNavController()
    val application = LocalContext.current.applicationContext as RecipesApplication
    val apiRecipe = RecipesApi
    val repository = RecipesRepository(
        recipesRemoteDataSource = RecipesRemoteDataSource(apiRecipe),
        recipesLocalDataSource = RecipesLocalDataSource(application.db.recipesDao())
    )
    val getAllRecipesUseCase =
        com.kqm.architectureclean.usecases.GetAllRecipesUseCase(repository = repository)
    val addRecipesUseCase =
        com.kqm.architectureclean.usecases.AddRecipeUseCase(repository = repository)
    val updateFavoriteUseCase =
        com.kqm.architectureclean.usecases.UpdateFavoriteUseCase(repository = repository)
    val deleteRecipeUseCase =
        com.kqm.architectureclean.usecases.DeleteRecipeUseCase(repository = repository)

    NavHost(navController = navController, startDestination = Home) {
        composable<Home> {
            RecipesScreen(viewModel = viewModel {
                RecipesViewModel(
                    getAllRecipesUseCase,
                    addRecipesUseCase,
                    updateFavoriteUseCase,
                    deleteRecipeUseCase
                )
            }, {
                navController.navigate(
                    RecipeDetail(it)
                )
            }, { navController.navigate(Home) })
        }

        composable<RecipeDetail> { backStackEntry ->
            val detail = backStackEntry.toRoute<RecipeDetail>()
            DetailScreen(id = detail.id, viewModel = viewModel {
                RecipesViewModel(
                    getAllRecipesUseCase,
                    addRecipesUseCase,
                    updateFavoriteUseCase,
                    deleteRecipeUseCase
                )
            })
            { navController.popBackStack() }
        }
    }
}
