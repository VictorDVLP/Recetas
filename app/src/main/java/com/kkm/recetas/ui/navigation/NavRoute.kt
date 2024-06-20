package com.kkm.recetas.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class RecipeDetail(val id: String)