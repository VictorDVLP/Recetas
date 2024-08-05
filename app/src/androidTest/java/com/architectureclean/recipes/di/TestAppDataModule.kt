package com.architectureclean.recipes.di

import android.app.Application
import androidx.room.Room
import com.architectureclean.recipes.framework.di.ApiUrl
import com.architectureclean.recipes.framework.di.FrameworkExtraModule
import com.architectureclean.recipes.framework.local.database.RoomRecipesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import javax.inject.Singleton

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [FrameworkExtraModule::class]
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
    @ApiUrl
    fun provideBaseUrl(): String = "http://localhost:8080/"
}