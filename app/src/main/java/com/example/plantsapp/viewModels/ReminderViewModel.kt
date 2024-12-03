package com.example.plantsapp.viewModels

import androidx.lifecycle.ViewModel
import com.example.plantsapp.data.model.reminder.WateringReminderModel
import com.example.plantsapp.data.repositories.WateringReminderRepository
import javax.inject.Inject

class ReminderViewModel @Inject constructor(private val wateringReminderRepository: WateringReminderRepository):ViewModel() {
    suspend fun getWateringReminders(date: String): List<WateringReminderModel> {
        return wateringReminderRepository.getRemindersByDate(date)
    }
}