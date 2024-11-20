package com.example.plantsapp.DI.viewModels

import com.example.plantsapp.Repositories.PlantsRepository
import com.example.plantsapp.ViewModels.MainViewModel
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
        plantsRepository: PlantsRepository):MainViewModel
    {
        return MainViewModel(plantsRepository)
    }
}