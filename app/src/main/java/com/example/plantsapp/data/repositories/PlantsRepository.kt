package com.example.plantsapp.data.repositories

import android.content.Context
import com.example.plantsapp.data.roomDb.DAO
import com.example.plantsapp.data.Plant
import com.example.plantsapp.data.model.ResponseModel
import com.example.plantsapp.data.remote.Api
import com.example.plantsapp.utils.internetCheck
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlantsRepository(private val dao: DAO, private val context: Context, private val api: Api) {
    suspend fun insertPlant(plant: Plant) {
        dao.insert(plant)
    }

    suspend fun deletePlant(plant: Plant) {
        dao.delete(plant)
    }

    suspend fun getAllPlants(): ResponseModel<List<Plant>>? {
        return withContext(Dispatchers.IO) {
            if (!internetCheck(context)) {
                // Fetch from API when the internet is available
                val apiResponse = api.getAllPlants().body()
                apiResponse?.data?.let { plants ->
                    // Insert fetched plants into the local database
                    dao.insertAll(plants)
                }
                apiResponse
            } else {
                // Fetch from the local database when the internet is not available
                val localPlants = dao.getAllPlants()
                if (localPlants.isNotEmpty()) {
                    ResponseModel(
                        status = 200,
                        isSuccessful = false,
                        message = "دیتا به صورت محلی بارگذاری شد",
                        data = localPlants
                    )
                } else {
                    ResponseModel(
                        status = 500,
                        isSuccessful = false,
                        message = "دیتایی یافت نشد !",
                        data = null
                    )
                }
            }
        }
    }


    suspend fun getPlantsByName(plantName: String): Plant? {
        return dao.getPlantByName(plantName)
    }
}
