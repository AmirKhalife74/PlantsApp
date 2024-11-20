package com.example.plantsapp.Data.RoomDb.model

import com.example.plantsapp.Data.Plant

data class Garden(
    val id:Int,
    val plants:MutableList<Plant>,
    val name :String
)
