package com.architectureclean.recipes.ui.screens

import com.kqm.architectureclean.presentation.ui.screens.detail.DetailStateImpl
import com.kqm.architectureclean.domain.Recipe

class DetailStateFake: com.kqm.architectureclean.presentation.ui.screens.detail.DetailStateImpl {

    var recipeShared: Recipe? = null

    override fun initIntent(recipe: Recipe) {
        recipeShared = recipe
    }
}