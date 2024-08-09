package com.architectureclean.recipes.ui.screens.recipes.resultComposables

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kqm.architectureclean.presentation.ResultCall


class ErrorScreenshotPreview {

    @Preview(showBackground = true)
    @Composable
    fun ErrorScreenPreview() {
        val state = com.kqm.architectureclean.presentation.ResultCall.Error(Exception("Network Error"))

        com.kqm.architectureclean.presentation.ui.screens.recipes.resultComposables.ErrorScreen(
            state = state,
            onBack = {})
    }
}