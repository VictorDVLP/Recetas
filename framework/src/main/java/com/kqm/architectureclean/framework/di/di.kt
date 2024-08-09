package com.kqm.architectureclean.framework.di

import android.app.Application
import androidx.room.Room
import com.kqm.architectureclean.framework.framework.RecipesLocalDataSource
import com.kqm.architectureclean.framework.framework.RecipesRemoteDataSource
import com.kqm.architectureclean.framework.framework.local.database.RecipesDao
import com.kqm.architectureclean.framework.framework.local.database.RoomRecipesDatabase
import com.kqm.architectureclean.framework.framework.remote.RecipesApi
import com.kqm.architectureclean.framework.framework.remote.RecipesService
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
    fun provideDao(database: RoomRecipesDatabase): RecipesDao {
        return database.recipesDao()
    }

    @Provides
    @Singleton
    fun provideServerApi(@ApiUrl baseUrl: String): RecipesApi = RecipesApi(baseUrl)

    @Provides
    @Singleton
    fun provideServerService(api: RecipesApi): RecipesService = api.retrofitService
}


@Module
@InstallIn(SingletonComponent::class)
object FrameworkExtraModule {
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
    @ApiUrl
    fun provideBaseUrl(): String = "https://www.themealdb.com/api/json/v1/1/"
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
