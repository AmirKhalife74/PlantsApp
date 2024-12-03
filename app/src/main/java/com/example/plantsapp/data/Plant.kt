package com.example.plantsapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class CareInstructions(
    val watering: String,
    val sunlight: String,
    val wateringInterval: Int
)
@Entity(tableName = "tbl_plants")
data class Plant(
    @PrimaryKey(autoGenerate = false) val id: String,
    val name: String,
    val description: String,
    val careInstructions: CareInstructions,
    val categories: List<String>,
    val price: Double,
    val stock: Int,
    val images: List<String>,
    val lastWateredDate: String?, // ISO format date (e.g., "2024-12-01")
    val nextWateringDate: String? // Calculated based on `lastWateredDate` and `wateringInterval`
):Serializable
