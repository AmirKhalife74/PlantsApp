package com.example.plantsapp.data.dependencyInjection.repoModules

import android.content.Context
import androidx.room.Room
import com.example.plantsapp.data.roomDb.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Provides
    @Singleton
    fun providePlantsDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context.applicationContext,
            Database::class.java,
            "app_database"
        ).build()

    }

    @Provides
    fun providePlantDao(database: Database) = database.roomDao()

    @Provides
    fun provideWateringReminderDao(database: Database) = database.wateringDao()
}