package com.example.plantsapp.Data.RoomDb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.plantsapp.Data.Garden
import com.example.plantsapp.Data.Plant

@Dao
interface DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(plant: Plant)

    @Delete
    fun delete(plant: Plant)

    @Update
    fun update(plant: Plant)

    @Query("SELECT * FROM tbl_plants")
    fun getAllPlants(): List<Plant>

    @Query("SELECT * FROM Garden where id = :id")
    suspend fun getGardenById(id:Int): Garden

    @Query("SELECT * FROM tbl_plants WHERE name = :name")
    fun getPlantByName(name: String?): Plant?

    @Insert
    suspend fun insertGarden(garden: Garden)

    @Update
    suspend fun updateGarden(garden: Garden)

    @Query("Select * From Garden")
    suspend fun getGardens():List<Garden>

    @Delete
    suspend fun deleteGarden(garden: Garden)




}