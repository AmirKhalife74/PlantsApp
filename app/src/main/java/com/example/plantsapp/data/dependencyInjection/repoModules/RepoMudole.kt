package com.example.plantsapp.data.dependencyInjection.repoModules

import android.content.Context
import com.example.plantsapp.data.roomDb.DAO
import com.example.plantsapp.data.remote.Api
import com.example.plantsapp.data.repositories.PlantsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoMudole {

    @Provides
    @Singleton
    fun providePlantsRepository(db: DAO, @ApplicationContext context: Context, api:Api): PlantsRepository {
        return PlantsRepository(db,context,api)
    }
}