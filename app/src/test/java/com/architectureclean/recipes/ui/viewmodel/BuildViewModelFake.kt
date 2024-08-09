package com.architectureclean.recipes.ui.viewmodel

import com.kqm.architectureclean.domain.Recipe
import com.kqm.architectureclean.presentation.ui.viewmodel.RecipesViewModel
import com.kqm.architectureclean.test.unit.fakes.buildRepositoryFake
import com.kqm.architectureclean.usecases.AddRecipeUseCase
import com.kqm.architectureclean.usecases.DeleteRecipeUseCase
import com.kqm.architectureclean.usecases.GetAllRecipesUseCase
import com.kqm.architectureclean.usecases.UpdateFavoriteUseCase

fun buildViewModelFake(localData: List<Recipe> = emptyList()): RecipesViewModel {

    val repository = buildRepositoryFake(localData)

    return RecipesViewModel(
        GetAllRecipesUseCase(repository),
        AddRecipeUseCase(repository),
        UpdateFavoriteUseCase(repository),
        DeleteRecipeUseCase(repository)
    )
}