package com.example.plantsapp.data.roomDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.plantsapp.data.model.reminder.WateringReminderModel

@Dao
interface WateringReminderDao {
    @Insert
    suspend fun insertReminder(reminder: WateringReminderModel)

    @Query("SELECT * FROM watering_reminders WHERE reminderDate = :date")
    suspend fun getRemindersByDate(date: String): List<WateringReminderModel>

    @Query("UPDATE watering_reminders SET isWatered = :status WHERE id = :reminderId")
    suspend fun updateWateredStatus(reminderId: Int, status: Boolean)
}