package com.kkm.recetas

import android.app.Application
import androidx.compose.runtime.Composable
import com.kkm.recetas.navigation.NavigationRecipes
import com.kkm.recetas.repository.RecipesRepository

@Composable
fun RecipesApplication(repository: RecipesRepository) {
    NavigationRecipes(repository)
}
