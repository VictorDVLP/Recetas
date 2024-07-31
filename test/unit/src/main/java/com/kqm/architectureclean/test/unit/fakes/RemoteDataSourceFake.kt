package com.kqm.architectureclean.test.unit.fakes

import com.kqm.architectureclean.data.RecipesRemoteDataSourceImpl
import com.kqm.architectureclean.domain.Recipe
import com.kqm.architectureclean.test.unit.helpers.generateRecipes

class RemoteDataSourceFake: RecipesRemoteDataSourceImpl {

    override suspend fun getRecipe(): List<Recipe> {

        return generateRecipes(5)
    }
}