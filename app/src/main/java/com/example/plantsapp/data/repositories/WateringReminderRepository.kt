package com.example.plantsapp.data.repositories

import com.example.plantsapp.data.model.reminder.WateringReminderModel
import com.example.plantsapp.data.roomDb.WateringReminderDao
import javax.inject.Inject

class WateringReminderRepository @Inject constructor(private val dao: WateringReminderDao) {

    suspend fun insertReminder(reminder: WateringReminderModel) {
        dao.insertReminder(reminder)
    }

    suspend fun getRemindersByDate(date: String): List<WateringReminderModel> {
        return dao.getRemindersByDate(date)
    }

    suspend fun updateWateredStatus(reminderId: Int, status: Boolean) {
        dao.updateWateredStatus(reminderId, status)
    }
}