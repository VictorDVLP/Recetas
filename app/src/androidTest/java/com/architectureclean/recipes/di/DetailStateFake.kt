package com.architectureclean.recipes.di

import com.architectureclean.recipes.ui.screens.detail.DetailStateImpl
import com.kqm.architectureclean.domain.Recipe

class DetailStateFake: DetailStateImpl {

    var recipeShared: Recipe? = null

    override fun initIntent(recipe: Recipe) {
        recipeShared = recipe
    }
}