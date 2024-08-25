package com.kqm.architectureclean.presentation.ui.screens.recipes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.kqm.architectureclean.presentation.ui.common.FloatingButton
import com.kqm.architectureclean.presentation.ui.screens.recipes.resultComposables.ErrorScreen
import com.kqm.architectureclean.presentation.ui.screens.recipes.resultComposables.LoadingIndicator
import com.kqm.architectureclean.presentation.ui.screens.recipes.resultComposables.SuccessScreen
import com.kqm.architectureclean.presentation.ui.viewmodel.RecipesViewModel
import com.kqm.architectureclean.domain.Recipe
import com.kqm.architectureclean.presentation.R
import com.kqm.architectureclean.presentation.ResultCall

@Composable
fun RecipesScreen(
    viewModel: RecipesViewModel = hiltViewModel(),
    onNavigateDetail: (String) -> Unit,
    onBackNavigate: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    RecipesScreen(state = state, addRecipe = { viewModel.addRecipe() }, onNavigateDetail, onBackNavigate)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipesScreen(
    state: ResultCall<List<Recipe>>,
    addRecipe: () -> Unit,
    onNavigateDetail: (String) -> Unit,
    onBackNavigate: () -> Unit
) {

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
            ) { addRecipe() }
        }
    ) { paddingValues ->
        when (state) {
            is ResultCall.Success -> {
                SuccessScreen(paddingValues, state, onNavigateDetail)
            }

            is ResultCall.Error -> {
                ErrorScreen(state) { onBackNavigate() }
            }

            is ResultCall.Loading -> {
                LoadingIndicator()
            }
        }

    }
}
