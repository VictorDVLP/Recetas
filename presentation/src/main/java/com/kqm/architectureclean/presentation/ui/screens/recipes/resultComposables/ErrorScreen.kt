package com.kqm.architectureclean.presentation.ui.screens.recipes.resultComposables

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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kqm.architectureclean.domain.Recipe
import com.kqm.architectureclean.presentation.ResultCall

const val BUTTON_RETRY_TEST_TAG = "button_retry"

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
        Button(onClick = { onBack() }, modifier = Modifier.testTag(BUTTON_RETRY_TEST_TAG)) {
            Text("Retry")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    val state = ResultCall.Error(Exception("Network Error"))

    ErrorScreen(state = state, onBack = {})
}
