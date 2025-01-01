package com.example.plantsapp.data.roomDb

import androidx.room.TypeConverter
import com.example.plantsapp.data.CareInstructions
import com.example.plantsapp.data.Plant
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


    @TypeConverter
    fun fromCareInstructionsToString(careInstructions: CareInstructions?): String? {
        return careInstructions?.let { Gson().toJson(it) }
    }

    @TypeConverter
    fun fromStringToCareInstructions(value: String?): CareInstructions? {
        return value?.let { Gson().fromJson(it, CareInstructions::class.java) }
    }

    // Convert List<String> to String (JSON representation)
    @TypeConverter
    fun fromListToString(list: List<String>?): String? {
        return list?.let { Gson().toJson(it) }
    }

    // Convert String (JSON format) back to List<String>
    @TypeConverter
    fun fromStringToList(json: String?): List<String>? {
        return json?.let {
            val type = object : TypeToken<List<String>>() {}.type
            Gson().fromJson(it, type)
        }
    }
}