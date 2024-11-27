package com.example.plantsapp.data.roomDb.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.plantsapp.data.Plant

@Entity
data class Cart(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val plants: MutableList<Plant>,
    val totalPrice:Int
)
