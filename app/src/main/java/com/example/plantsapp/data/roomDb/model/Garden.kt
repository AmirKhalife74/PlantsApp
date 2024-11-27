package com.example.plantsapp.data.roomDb.model

import com.example.plantsapp.data.Plant

data class Garden(
    val id:Int,
    val plants:MutableList<Plant>,
    val name :String
)
