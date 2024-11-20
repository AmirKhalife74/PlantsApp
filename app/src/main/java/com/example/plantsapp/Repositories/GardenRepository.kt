package com.example.plantsapp.Repositories

import android.content.Context
import com.example.plantsapp.Data.Garden
import com.example.plantsapp.Data.Plant
import com.example.plantsapp.Data.Remote.Api
import com.example.plantsapp.Data.RoomDb.DAO
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GardenRepository @Inject constructor(
    private val dao: DAO,
    @ApplicationContext private val context: Context,
    private val api: Api
) {
    suspend fun getAllGardens(): MutableList<Garden> {
        return dao.getGardens().toMutableList()
    }

    suspend fun deleteGarden(garden: Garden) {
        dao.deleteGarden(garden)
    }

    suspend fun createGarden(plantList: MutableList<Plant>, gardenName: String) {
        return withContext(Dispatchers.IO) {
            val garden = Garden(0, gardenName, plantList)
            dao.insertGarden(garden)
        }
    }

    suspend fun updateGarden(garden: Garden) {
        dao.updateGarden(garden)
    }

    suspend fun getGardenById(id: Int): Garden {
        return dao.getGardenById(id)
    }

    suspend fun addPlantToGarden(garden: Garden)
    {
        dao.updateGarden(garden)
    }
}