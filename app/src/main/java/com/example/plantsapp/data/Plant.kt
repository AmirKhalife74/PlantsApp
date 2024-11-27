package com.example.plantsapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity(tableName = "tbl_plants")
data class Plant(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val  name:String?,
    val  desc:String?,
    val  fullName:String?,
    val  picture:String?,
    val  temp:Int?,
    val  difficulty:Int?, // Between 1 and 5
    val  light:Int?,
    val  pot:String?,
    val  water:String?,
    val  price:Int?,
    val height:Int?,
):Serializable
