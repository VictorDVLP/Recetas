package com.kqm.architectureclean.presentation.ui.screens.recipes.resultComposables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.kqm.architectureclean.domain.Recipe
import com.kqm.architectureclean.presentation.ResultCall

private const val SUCCESS_SCREEN_TEST_TAG = "Success_screen_test_tag"

@Composable
fun SuccessScreen(
    paddingValues: PaddingValues,
    state: ResultCall<List<Recipe>>,
    onNavigateDetail: (String) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier
            .padding(paddingValues)
            .testTag(SUCCESS_SCREEN_TEST_TAG),
        columns = GridCells.Adaptive(180.dp)
    ) {
        items((state as ResultCall.Success<List<Recipe>>).data) { recipe ->
            RecipeItem(
                recipe = recipe,
                modifier = Modifier.clickable { onNavigateDetail(recipe.id) })
        }
    }
}


@Composable
private fun RecipeItem(recipe: Recipe, modifier: Modifier = Modifier) {
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
                if (recipe.favorite) {
                    Image(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Favorite",
                        modifier = Modifier
                            .padding(top = 12.dp, end = 8.dp)
                            .align(Alignment.TopEnd),
                        colorFilter = ColorFilter.tint(Color.Red)
                    )
                }
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

@Preview(showBackground = true)
@Composable
fun SuccessScreenPreview() {
    val recipe = listOf(Recipe(
        id = "1",
        name = "Recipe 1",
        imageThumb = "https://www.themealdb.com/images/media/meals/st1ifa1583267248.jpg",
        videoThumb = "",
        area = "Area 1",
        category = "Category 1",
        instructions = "Instructions 1",
        ingredients = listOf("Ingredient 1", "Ingredient 2", "Ingredient 3"),
        measures = listOf("Measure 1", "Measure 2", "Measure 3"),
        favorite = false
    ), Recipe(
        id = "2",
        name = "Recipe 2",
        imageThumb = "https://www.themealdb.com/images/media/meals/st1ifa1583267248.jpg",
        videoThumb = "",
        area = "Area 2",
        category = "Category 2",
        instructions = "Instructions 2",
        ingredients = listOf("Ingredient 1", "Ingredient 2", "Ingredient 3"),
        measures = listOf("Measure 1", "Measure 2", "Measure 3"),
        favorite = false
    ), Recipe(
        id = "3",
        name = "Recipe 3",
        imageThumb = "https://www.themealdb.com/images/media/meals/st1ifa1583267248.jpg",
        videoThumb = "",
        area = "Area 3",
        category = "Category 3",
        instructions = "Instructions 3",
        ingredients = listOf("Ingredient 1", "Ingredient 2", "Ingredient 3"),
        measures = listOf("Measure 1", "Measure 2", "Measure 3"),
        favorite = false
    ))
    val state = ResultCall.Success(recipe)
    SuccessScreen(PaddingValues(), state) {}
}