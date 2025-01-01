package com.example.plantsapp.data.repositories

import android.content.Context
import com.example.plantsapp.data.Garden
import com.example.plantsapp.data.Plant
import com.example.plantsapp.data.remote.Api
import com.example.plantsapp.data.roomDb.DAO
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

    suspend fun createGarden(garden: Garden) {
        return withContext(Dispatchers.IO) {
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