package com.kkm.recipes.ui.screens.detail

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.kkm.recipes.domain.Recipe

class DetailState(private val context: Context) {
    fun initIntent(recipe: Recipe) {

        Intent(Intent.ACTION_SEND).also { it.type = "text/plain" }
            .putExtra(Intent.EXTRA_TEXT, formattedRecipe(recipe))
            .let { intent -> ContextCompat.startActivity(context, intent, null) }
    }
}

@Composable
fun rememberDetailState(context: Context = LocalContext.current): DetailState {
    return remember { DetailState(context) }
}


private fun formattedRecipe(recipe: Recipe): String {
    return """
Nombre: ${recipe.name}
Imagen: ${recipe.imageThumb}
Zona: ${recipe.area}
Categoría: ${recipe.category}

Ingredientes:
${recipe.ingredients.joinToString("\n") { it }}

Medidas:
${recipe.measures.joinToString("\n") { it }}

Instrucciones:
${recipe.instructions}
""".trimIndent()
}
