package com.kkm.recetas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkm.recetas.data.local.model.Recipe
import com.kkm.recetas.repository.RecipesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RecipesViewModel(private val repository: RecipesRepository) : ViewModel() {

    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val state get() = _state.asStateFlow()


    init {
        getRecipes()
    }

    private fun getRecipes() {
        viewModelScope.launch {
            repository.recipes.collect {
                _state.value = _state.value.copy(isLoading = false, recipes = it)
            }
        }
    }

    fun addRecipe() {
        viewModelScope.launch {
            repository.addRecipe()
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