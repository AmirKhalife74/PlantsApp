package com.example.plantsapp.data.dependencyInjection.viewModels

import com.example.plantsapp.data.repositories.PlantsRepository
import com.example.plantsapp.viewModels.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelsModule {

    @Provides
    @Singleton
    fun provideMainViewModel(
        plantsRepository: PlantsRepository
    ):MainViewModel
    {
        return MainViewModel(plantsRepository)
    }
}