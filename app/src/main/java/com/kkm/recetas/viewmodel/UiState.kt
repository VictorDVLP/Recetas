package com.kkm.recetas.viewmodel

import android.content.Intent
import com.kkm.recetas.data.local.model.Recipe

data class UiState(
    val isLoading: Boolean = false,
    val recipes: List<Recipe> = emptyList(),
)
