package com.kkm.recetas.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkm.recetas.ResultCall
import com.kkm.recetas.domain.Recipe
import com.kkm.recetas.repository.RecipesRepository
import com.kkm.recetas.stateAsResultIn
import com.kkm.recetas.usecases.AddRecipeUseCase
import com.kkm.recetas.usecases.DeleteRecipeUseCase
import com.kkm.recetas.usecases.GetAllRecipesUseCase
import com.kkm.recetas.usecases.UpdateFavoriteUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipesViewModel(
    getAllRecipesUseCase: GetAllRecipesUseCase,
    private val addRecipeUseCase: AddRecipeUseCase,
    private val updateFavoriteUseCase: UpdateFavoriteUseCase,
    private val deleteRecipeUseCase: DeleteRecipeUseCase
) : ViewModel() {

    val state: StateFlow<ResultCall<List<Recipe>>> = getAllRecipesUseCase()
        .stateAsResultIn(viewModelScope)


    fun addRecipe() {
        viewModelScope.launch {
            addRecipeUseCase()
        }
    }

    fun updateFavorite(recipe: Recipe) {
        viewModelScope.launch {
            updateFavoriteUseCase(recipe)
        }
    }

    fun deleteRecipe(recipe: Recipe) {
        viewModelScope.launch {
            deleteRecipeUseCase(recipe)
        }

    }
}