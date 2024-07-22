package com.architectureclean.recipes.ui.screens.recipes.resultComposables

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
import com.architectureclean.recipes.ResultCall
import com.kqm.architectureclean.domain.Recipe

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
