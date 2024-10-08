package com.kqm.architectureclean.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.kqm.architectureclean.domain.Recipe
import com.kqm.architectureclean.presentation.ResultCall
import com.kqm.architectureclean.presentation.stateAsResultIn
import com.kqm.architectureclean.usecases.AddRecipeUseCase
import com.kqm.architectureclean.usecases.DeleteRecipeUseCase
import com.kqm.architectureclean.usecases.GetAllRecipesUseCase
import com.kqm.architectureclean.usecases.UpdateFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
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