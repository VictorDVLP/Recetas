package com.kkm.recetas.ui.screens.recipes.resultComposables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kkm.recetas.ResultCall
import com.kkm.recetas.data.local.model.Recipe
import com.kkm.recetas.viewmodel.RecipesViewModel

@Composable
fun ErrorScreen(
    state: ResultCall<List<Recipe>>,
    onBack : () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "${(state as ResultCall.Error).exception.message}")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onBack() }) {
            Text("Retry")
        }
    }
}
