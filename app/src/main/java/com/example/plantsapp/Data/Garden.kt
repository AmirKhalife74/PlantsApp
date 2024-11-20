package com.example.plantsapp.Data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Garden")
data class Garden(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name: String,
    val plants: MutableList<Plant>
):Serializable
