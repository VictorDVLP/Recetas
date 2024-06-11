package com.kkm.recetas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkm.recetas.ResultCall
import com.kkm.recetas.data.local.model.Recipe
import com.kkm.recetas.repository.RecipesRepository
import com.kkm.recetas.stateAsResultIn
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipesViewModel(private val repository: RecipesRepository) : ViewModel() {

    val state: StateFlow<ResultCall<List<Recipe>>> = repository.recipes
        .stateAsResultIn(viewModelScope)


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