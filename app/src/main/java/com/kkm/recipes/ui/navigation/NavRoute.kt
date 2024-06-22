package com.kkm.recipes.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class RecipeDetail(val id: String)