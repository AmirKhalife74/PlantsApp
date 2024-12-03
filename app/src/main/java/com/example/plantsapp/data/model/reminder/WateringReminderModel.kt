package com.example.plantsapp.data.model.reminder

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "watering_reminders")
class WateringReminderModel (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val plantId: String,
    val gardenId: Int,
    val reminderDate: String, // Format: "YYYY-MM-DD"
    val isWatered: Boolean = false
)