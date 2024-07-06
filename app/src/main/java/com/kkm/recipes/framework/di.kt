package com.kkm.recipes.framework

import android.app.Application
import androidx.room.Room
import com.kkm.recipes.framework.local.database.RecipesDao
import com.kkm.recipes.framework.local.database.RoomRecipesDatabase
import com.kkm.recipes.framework.remote.RecipesApi
import com.kkm.recipes.framework.remote.RecipesService
import com.kqm.architectureclean.data.RecipesLocalDataSourceImpl
import com.kqm.architectureclean.data.RecipesRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object FrameworkModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): RoomRecipesDatabase {
        return Room.databaseBuilder(
            application,
            RoomRecipesDatabase::class.java,
            "recipes-db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(database: RoomRecipesDatabase): RecipesDao {
        return database.recipesDao()
    }

    @Provides
    @Singleton
    fun provideServerApi(): RecipesApi = RecipesApi

    @Provides
    @Singleton
    fun provideServerService(api: RecipesApi): RecipesService = api.retrofitService
}


@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {

    @Binds
    abstract fun bindRecipesLocalDataSource(
        recipesLocalDataSource: RecipesLocalDataSource
    ): RecipesLocalDataSourceImpl

    @Binds
    abstract fun bindRecipesRemoteDataSource(
        recipesRemoteDataSource: RecipesRemoteDataSource
    ): RecipesRemoteDataSourceImpl
}
