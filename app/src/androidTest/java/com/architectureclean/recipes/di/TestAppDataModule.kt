package com.architectureclean.recipes.di

import com.architectureclean.recipes.framework.DataSourceModule
import com.kqm.architectureclean.data.RecipesLocalDataSourceImpl
import com.kqm.architectureclean.data.RecipesRemoteDataSourceImpl
import com.kqm.architectureclean.domain.Recipe
import com.kqm.architectureclean.test.unit.fakes.LocalDataSourceFake
import com.kqm.architectureclean.test.unit.fakes.RemoteDataSourceFake
import com.kqm.architectureclean.test.unit.helpers.generateRecipes
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataSourceModule::class]
)

@Module
object TestAppDataModule {

    @Provides
    @Singleton
    fun provideLocalDataSourceFake() = LocalDataSourceFake()

    @Provides
    @Singleton
    fun provideRemoteDataSourceFake() = RemoteDataSourceFake()

    @Provides
    @Singleton
    fun provideLocalDataSource(fake: LocalDataSourceFake): RecipesLocalDataSourceImpl = fake

    @Provides
    @Singleton
    fun provideRemoteDataSource(fake: RemoteDataSourceFake): RecipesRemoteDataSourceImpl = fake
}