package com.example.plantsapp.Data.RoomDb

import androidx.room.TypeConverter
import com.example.plantsapp.Data.Plant
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {
    @TypeConverter
    fun fromPlantList(plants: List<Plant>?): String? {
        return if (plants == null) {
            null
        } else {
            Gson().toJson(plants)  // Convert the list to JSON string
        }
    }

    @TypeConverter
    fun toPlantList(plantsString: String?): List<Plant>? {
        return if (plantsString == null) {
            null
        } else {
            val type = object : TypeToken<List<Plant>>() {}.type
            Gson().fromJson(plantsString, type)  // Convert the JSON string back to a List
        }
    }
}