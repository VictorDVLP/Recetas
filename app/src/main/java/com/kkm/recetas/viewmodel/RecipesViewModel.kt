package com.kkm.recetas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkm.recetas.data.local.model.Recipe
import com.kkm.recetas.repository.RecipesRepository
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RecipesViewModel(private val repository: RecipesRepository) : ViewModel() {

    val state: StateFlow<UiState> = repository.recipes
       .map { value: List<Recipe> -> UiState(recipes = value) }
       .stateIn(
           scope = viewModelScope,
           started = WhileSubscribed(5000),
           initialValue = UiState(isLoading = true)
       )


    fun addRecipe() {
        viewModelScope.launch {
            repository.addRecipe()
        }
    }

    fun updateFavorite(recipe: Recipe) {
            viewModelScope.launch {
                repository.updateFavorite(recipe)
        }
    }

    fun deleteRecipe(recipe: Recipe) {
            viewModelScope.launch {
                repository.deleteRecipe(recipe)
            }

    }

    fun formattedRecipe(recipe: Recipe): String {
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
}