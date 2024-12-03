package com.example.plantsapp.data.roomDb

import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.plantsapp.data.Garden
import com.example.plantsapp.data.Plant
import com.example.plantsapp.data.model.reminder.WateringReminderModel

@androidx.room.Database(entities = [Plant::class,Garden::class,WateringReminderModel::class], version = 4)
@TypeConverters(Converter::class)
abstract class Database : RoomDatabase() {
    abstract fun roomDao(): DAO
    abstract fun wateringDao(): WateringReminderDao
}