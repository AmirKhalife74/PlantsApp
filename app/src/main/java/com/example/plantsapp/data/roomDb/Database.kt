package com.example.plantsapp.data.roomDb

import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.plantsapp.data.Garden
import com.example.plantsapp.data.Plant

@androidx.room.Database(entities = [Plant::class,Garden::class], version = 2)
@TypeConverters(Converter::class)
abstract class Database : RoomDatabase() {
    abstract fun roomDao(): DAO
}