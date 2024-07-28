package com.kqm.architectureclean.test.unit.fakes

import com.kqm.architectureclean.data.RecipesRemoteDataSourceImpl
import com.kqm.architectureclean.domain.Recipe
import com.kqm.architectureclean.test.unit.helpers.generateRecipes

class RemoteDataSourceFake(remoteData: List<Recipe>): RecipesRemoteDataSourceImpl {

    private val recipesRemote = remoteData

    override suspend fun getRecipe(): List<Recipe> {
        val lastId = recipesRemote.last().id.toInt()
        val newId = lastId + 1
        val newRecipe = generateRecipes(newId).last()
        return recipesRemote + newRecipe
    }
}