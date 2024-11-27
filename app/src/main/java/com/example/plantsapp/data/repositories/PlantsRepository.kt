package com.example.plantsapp.data.repositories

import android.content.Context
import android.widget.Toast
import com.example.plantsapp.data.roomDb.DAO
import com.example.plantsapp.data.Plant
import com.example.plantsapp.data.model.ResponseModel
import com.example.plantsapp.data.remote.Api
import com.example.plantsapp.utils.internetCheck
import retrofit2.Response

class PlantsRepository(private val dao: DAO, private val context: Context, private val api: Api) {
    suspend fun insertPlant(plant: Plant) {
        dao.insert(plant)
    }

    suspend fun deletePlant(plant: Plant) {
        dao.delete(plant)
    }

    suspend fun getAllPlants(): ResponseModel<List<Plant>>? {
        var plantList: ResponseModel<List<Plant>>? = null
        if (internetCheck(context) {
                Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show()
            }) {
            plantList = api.getAllPlants().body()
            plantList?.data.let {
                it?.forEach {
                    dao.insert(it)
                }
            }

            return plantList
        } else {
            if (dao.getAllPlants().isNotEmpty())
            {
                plantList = ResponseModel(
                    status = 200,
                    isSuccessful = false,
                    "دیتا به صورت محلی بارگذاری شد",
                    data = dao.getAllPlants()
                )
            }else
            {
                plantList = ResponseModel(
                    status = 500,
                    isSuccessful = false,
                    "دیتایی یافت نشد !",
                    data =null
                )
            }

            return plantList
        }
    }

    suspend fun getPlantsByName(plantName: String): Plant? {
        return dao.getPlantByName(plantName)
    }
}