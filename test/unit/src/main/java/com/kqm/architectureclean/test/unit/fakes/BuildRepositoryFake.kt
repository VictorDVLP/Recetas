package com.kqm.architectureclean.test.unit.fakes

import com.kqm.architectureclean.data.RecipesLocalDataSourceImpl
import com.kqm.architectureclean.data.RecipesRemoteDataSourceImpl
import com.kqm.architectureclean.data.RecipesRepository
import com.kqm.architectureclean.domain.Recipe

fun buildRepositoryFake(
    localData: List<Recipe>
): RecipesRepository {

    val localDataSource: RecipesLocalDataSourceImpl =
        LocalDataSourceFake(localData)
    val remoteDataSource: RecipesRemoteDataSourceImpl =
        RemoteDataSourceFake()

    return RecipesRepository(remoteDataSource, localDataSource)
}
