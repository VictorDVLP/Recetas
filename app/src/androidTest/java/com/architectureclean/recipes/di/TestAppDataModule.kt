package com.architectureclean.recipes.di

import android.app.Application
import androidx.room.Room
import com.architectureclean.recipes.framework.DataSourceModule
import com.architectureclean.recipes.framework.FrameworkExtraModule
import com.architectureclean.recipes.framework.local.database.RoomRecipesDatabase
import com.kqm.architectureclean.data.RecipesLocalDataSourceImpl
import com.kqm.architectureclean.data.RecipesRemoteDataSourceImpl
import com.kqm.architectureclean.test.unit.fakes.LocalDataSourceFake
import com.kqm.architectureclean.test.unit.fakes.RemoteDataSourceFake
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import javax.inject.Singleton

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataSourceModule::class, FrameworkExtraModule::class]
)

@Module
object TestAppDataModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): RoomRecipesDatabase {
        return Room.inMemoryDatabaseBuilder(application, RoomRecipesDatabase::class.java)
            .setTransactionExecutor(Dispatchers.Main.asExecutor())
            .allowMainThreadQueries()
            .build()
    }

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