package com.kkm.recetas.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.kkm.recetas.repository.RecipesRepository
import com.kkm.recetas.viewmodel.RecipesViewModel

@Composable
fun RecipesScreen(repository: RecipesRepository) {

    val viewModel: RecipesViewModel = viewModel { RecipesViewModel(repository = repository) }
    val state by viewModel.state.collectAsState()

    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (state.recipes.isNotEmpty()) {
                item {
                    Box {
                        Image(
                            painter = rememberAsyncImagePainter(model = state.recipes[0].imageThumb),
                            contentDescription = state.recipes[0].name,
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                                .aspectRatio(2 / 3f),
                            contentScale = ContentScale.Fit
                        )
                    }

                    Text(
                        text = state.recipes[0].name,
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = state.recipes[0].area,
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Start
                    )

                    Text(
                        text = state.recipes[0].category,
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Start
                    )

                    Row(
                        horizontalArrangement = Arrangement.Start
                    ) {
                        ListIngredients(state.recipes[0].ingredients)
                        ListMeasures(state.recipes[0].measures)
                    }
                    Box {
                        Instructions(state.recipes[0].instructions)
                    }
                }
            }
        }
    }
}

@Composable
fun ListIngredients(ingredients: List<String> ) {
    Text(
        text = "Ingredients:",
        style = MaterialTheme.typography.titleMedium
    )
    Spacer(modifier = Modifier.height(8.dp))
    LazyColumn(modifier = Modifier.height(100.dp)) {
        items(ingredients) { ingredient ->
            Text(
                text = " · $ingredient",
                modifier = Modifier
                    .padding(horizontal = 6.dp, vertical = 2.dp),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun ListMeasures( measures: List<String> ) {
    Text(
        text = "Measures:",
        style = MaterialTheme.typography.titleMedium
    )
    Spacer(modifier = Modifier.height(8.dp))
    LazyColumn(modifier = Modifier.height(100.dp)) {
        items(measures) { measure ->
            Text(
                text = " · $measure",
                modifier = Modifier
                    .padding(horizontal = 6.dp, vertical = 2.dp),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun Instructions( instructions: String ) {
    Column {
    Text(
        text = "Instructions",
        modifier = Modifier.fillMaxWidth(),
        style = MaterialTheme.typography.titleLarge
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = instructions,
        modifier = Modifier.fillMaxWidth(),
        style = MaterialTheme.typography.bodyMedium
    )
    }
}