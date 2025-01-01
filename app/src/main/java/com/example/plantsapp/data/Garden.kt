package com.example.plantsapp.data

import androidx.annotation.ColorRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Garden")
data class Garden(
    @PrimaryKey(autoGenerate = true)
    val id:Int?,
    var name: String,
    var details: String,
    var plants: MutableList<Plant>,
   @ColorRes var color:Int,
    var count:String = ""
):Serializable
{
    init {
        count ="${plants.size} تعداد : "
    }

}