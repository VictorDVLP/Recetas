package com.kkm.recetas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkm.recetas.repository.RecipesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RecipesViewModel( private val repository: RecipesRepository): ViewModel() {

    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val state get() = _state

    init {
        getRecipes()
    }

    private fun getRecipes() {
        viewModelScope.launch {
            _state.value = UiState(isLoading = true)
            _state.value = UiState(recipes = repository.getRecipe())
            _state.value = UiState(isLoading = false)
        }
    }

}