package com.example.plantsapp.data.dependencyInjection.viewModels

import com.example.plantsapp.data.repositories.PlantsRepository
import com.example.plantsapp.data.repositories.UserRepository
import com.example.plantsapp.data.repositories.WateringReminderRepository
import com.example.plantsapp.viewModels.MainViewModel
import com.example.plantsapp.viewModels.ReminderViewModel
import com.example.plantsapp.viewModels.UserViewModel
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

    @Provides
    @Singleton
    fun provideUserViewModel(userRepository: UserRepository): UserViewModel
    {
        return UserViewModel(userRepository)
    }

    @Provides
    @Singleton
    fun provideReminderViewModel(userRepository: WateringReminderRepository): ReminderViewModel
    {
        return ReminderViewModel(userRepository)
    }
}