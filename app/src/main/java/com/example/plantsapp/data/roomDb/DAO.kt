package com.example.plantsapp.data.roomDb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.plantsapp.data.Garden
import com.example.plantsapp.data.Plant

@Dao
interface DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(plant: Plant)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plants:List<Plant>)
    @Delete
    suspend fun delete(plant: Plant)

    @Update
    suspend fun update(plant: Plant)

    @Query("SELECT * FROM tbl_plants")
    suspend fun getAllPlants(): List<Plant>

    @Query("SELECT * FROM Garden where id = :id")
    suspend fun getGardenById(id:Int): Garden

    @Query("SELECT * FROM tbl_plants WHERE name = :name")
    suspend fun getPlantByName(name: String?): Plant?

    @Insert
    suspend fun insertGarden(garden: Garden)

    @Update
    suspend fun updateGarden(garden: Garden)

    @Query("Select * From Garden")
    suspend fun getGardens():List<Garden>

    @Delete
    suspend fun deleteGarden(garden: Garden)




}