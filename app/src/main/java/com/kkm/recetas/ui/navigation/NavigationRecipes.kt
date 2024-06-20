package com.kkm.recetas.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.kkm.recetas.RecipesApplication
import com.kkm.recetas.repository.RecipesLocalDataSource
import com.kkm.recetas.repository.RecipesRemoteDataSource
import com.kkm.recetas.repository.RecipesRepository
import com.kkm.recetas.ui.screens.detail.DetailScreen
import com.kkm.recetas.ui.screens.recipes.RecipesScreen
import com.kkm.recetas.ui.viewmodel.RecipesViewModel
import com.kkm.recetas.usecases.AddRecipeUseCase
import com.kkm.recetas.usecases.DeleteRecipeUseCase
import com.kkm.recetas.usecases.GetAllRecipesUseCase
import com.kkm.recetas.usecases.UpdateFavoriteUseCase


@Composable
fun NavigationRecipes() {
    val navController = rememberNavController()
    val application = LocalContext.current.applicationContext as RecipesApplication
    val repository = RecipesRepository(
        recipesRemoteDataSource = RecipesRemoteDataSource(),
        recipesLocalDataSource = RecipesLocalDataSource(application.db.recipesDao())
    )
    val getAllRecipesUseCase = GetAllRecipesUseCase(repository = repository)
    val addRecipesUseCase = AddRecipeUseCase(repository = repository)
    val updateFavoriteUseCase = UpdateFavoriteUseCase(repository = repository)
    val deleteRecipeUseCase = DeleteRecipeUseCase(repository = repository)

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
