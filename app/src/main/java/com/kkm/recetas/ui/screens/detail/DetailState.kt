package com.kkm.recetas.ui.screens.detail

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.kkm.recetas.data.local.model.Recipe

class DetailState(private val context: Context) {
    fun initIntent(recipe: Recipe, onFormatted: (Recipe) -> String) {

        Intent(Intent.ACTION_SEND).also { it.type = "text/plain" }
            .putExtra(Intent.EXTRA_TEXT, onFormatted(recipe))
            .let { intent -> ContextCompat.startActivity(context, intent, null) }
    }
}

@Composable
fun rememberDetailState(context: Context = LocalContext.current): DetailState {
   return remember { DetailState(context) }
}