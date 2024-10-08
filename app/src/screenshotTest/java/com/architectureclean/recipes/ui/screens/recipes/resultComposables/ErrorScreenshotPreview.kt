package com.architectureclean.recipes.ui.screens.recipes.resultComposables

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kqm.architectureclean.presentation.ResultCall
import com.kqm.architectureclean.presentation.ui.screens.recipes.resultComposables.ErrorScreen


class ErrorScreenshotPreview {

    @Preview(showBackground = true)
    @Composable
    fun ErrorScreenPreview() {
        val state = ResultCall.Error(Exception("Network Error"))

        ErrorScreen(
            state = state,
            onBack = {})
    }
}