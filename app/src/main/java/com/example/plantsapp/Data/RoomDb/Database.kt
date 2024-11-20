package com.example.plantsapp.Data.RoomDb

import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.plantsapp.Data.Garden
import com.example.plantsapp.Data.Plant

@androidx.room.Database(entities = [Plant::class,Garden::class], version = 2)
@TypeConverters(Converter::class)
abstract class Database : RoomDatabase() {
    abstract fun roomDao(): DAO
}