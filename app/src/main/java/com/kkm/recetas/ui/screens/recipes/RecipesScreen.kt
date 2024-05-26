@file:OptIn(ExperimentalMaterial3Api::class)

package com.kkm.recetas.ui.screens.recipes

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.kkm.recetas.R
import com.kkm.recetas.data.local.model.Recipe
import com.kkm.recetas.repository.RecipesRepository
import com.kkm.recetas.ui.common.floating.FloatingButton
import com.kkm.recetas.viewmodel.RecipesViewModel

@Composable
fun RecipesScreen(repository: RecipesRepository, onNavigateDetail: (String) -> Unit) {

    val viewModel: RecipesViewModel = viewModel { RecipesViewModel(repository) }
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
        floatingActionButton = {
            FloatingButton(
                description = "Add Recipe",
                icon = Icons.Filled.Add
            ) { viewModel.getRecipes() }
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            modifier = Modifier.padding(paddingValues),
            columns = GridCells.Adaptive(180.dp)
        ) {
            items(state.recipes) { recipe ->
                RecipeItem(
                    recipe = recipe,
                    modifier = Modifier.clickable { onNavigateDetail(recipe.id) })
            }
        }
    }
}

@Composable
fun RecipeItem(recipe: Recipe, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.padding(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = MaterialTheme.shapes.small
    ) {
        Column {
            Box {
                Image(
                    painter = rememberAsyncImagePainter(model = recipe.imageThumb),
                    contentDescription = recipe.name,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 4.dp)
                        .aspectRatio(2 / 3f),
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                text = recipe.name,
                modifier = modifier
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                fontStyle = MaterialTheme.typography.bodySmall.fontStyle
            )
        }
    }
}