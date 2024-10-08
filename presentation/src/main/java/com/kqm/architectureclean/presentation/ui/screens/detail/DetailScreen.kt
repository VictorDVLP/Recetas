package com.kqm.architectureclean.presentation.ui.screens.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.kqm.architectureclean.presentation.ui.common.FloatingButton
import com.kqm.architectureclean.presentation.ui.common.TopBarApp
import com.kqm.architectureclean.presentation.ui.viewmodel.RecipesViewModel
import com.kqm.architectureclean.domain.Recipe
import com.kqm.architectureclean.presentation.ResultCall

@Composable
fun DetailScreen(id: String, viewModel: RecipesViewModel = hiltViewModel(), onBack: () -> Unit) {
    val state by viewModel.state.collectAsState()
    val detailState = rememberDetailState()


    val recipe = (state as? ResultCall.Success<List<Recipe>>)?.data?.find { it.id == id }

    if (recipe != null) {
        DetailScreen(
            recipe = recipe,
            detailState = detailState,
            onDeleteClick = viewModel::deleteRecipe,
            onFavoriteClick = viewModel::updateFavorite,
            onBack = onBack
        )
    }
}

@Composable
fun DetailScreen(
    recipe: Recipe,
    detailState: DetailStateImpl,
    onDeleteClick: (Recipe) -> Unit,
    onFavoriteClick: (Recipe) -> Unit,
    onBack: () -> Unit
) {
    recipe.let {

        Scaffold(
            contentColor = MaterialTheme.colorScheme.onBackground,
            topBar = {
                TopBarApp(
                    { onBack() },
                    { detailState.initIntent(recipe = it) },
                    { onDeleteClick(it); onBack() })
            },
            floatingActionButton = {
                FloatingButton(
                    description = "favorite",
                    icon = if (recipe.favorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
                ) {
                    onFavoriteClick(recipe)
                }
            },
            modifier = Modifier.fillMaxSize()
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier.padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
                    Box {
                        Image(
                            painter = rememberAsyncImagePainter(model = it.imageThumb),
                            contentDescription = it.name,
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                                .aspectRatio(16 / 9f),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Text(
                        text = it.name,
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.displaySmall,
                        textAlign = TextAlign.Center
                    )

                    Row(
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = it.area,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(end = 8.dp),
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = it.category,
                            fontSize = 20.sp,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 16.dp)
                    ) {
                        ListsRecipe(it.ingredients, "Ingredients:")
                        ListsRecipe(it.measures, "Measures:")
                    }
                    Box {
                        Instructions(it.instructions)
                    }
                }
            }
        }
    }
}

@Composable
fun Instructions(instructions: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
    ) {
        Text(
            text = "Instructions",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = instructions,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun ListsRecipe(list: List<String>, title: String) {
    Column {
        Text(
            text = title,
            fontSize = 22.sp,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Surface(
            shape = MaterialTheme.shapes.small,
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .height(100.dp)
                .padding(start = 1.dp, end = 1.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .widthIn(min = 200.dp)
                    .background(MaterialTheme.colorScheme.inversePrimary),
            ) {
                items(list) { item ->
                    Text(
                        text = if (item.isEmpty()) "" else "· $item",
                        fontSize = 16.sp,
                        modifier = Modifier
                            .height(38.dp)
                            .padding(horizontal = 6.dp, vertical = 2.dp),
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 2,
                        overflow = TextOverflow.Visible
                    )
                }
            }
        }
    }
}