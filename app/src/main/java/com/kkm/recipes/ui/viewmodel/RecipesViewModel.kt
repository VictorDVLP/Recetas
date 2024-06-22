package com.kkm.recipes.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkm.recipes.ResultCall
import com.kqm.architectureclean.domain.Recipe
import com.kkm.recipes.stateAsResultIn
import com.kkm.recipes.usecases.AddRecipeUseCase
import com.kkm.recipes.usecases.DeleteRecipeUseCase
import com.kkm.recipes.usecases.GetAllRecipesUseCase
import com.kkm.recipes.usecases.UpdateFavoriteUseCase
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