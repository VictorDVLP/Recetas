package com.kqm.architectureclean.test.unit.fakes

import com.kqm.architectureclean.data.RecipesLocalDataSourceImpl
import com.kqm.architectureclean.data.RecipesRemoteDataSourceImpl
import com.kqm.architectureclean.data.RecipesRepository
import com.kqm.architectureclean.domain.Recipe
import com.kqm.architectureclean.test.unit.helpers.generateRecipes

fun buildRepositoryFake(localData: List<Recipe> = emptyList(), remoteData: List<Recipe> = emptyList()): RecipesRepository {

    val localDataSource: RecipesLocalDataSourceImpl = LocalDataSourceFake(localData)
    val remoteDataSource: RecipesRemoteDataSourceImpl = RemoteDataSourceFake(remoteData)

    return RecipesRepository(remoteDataSource, localDataSource)
}
