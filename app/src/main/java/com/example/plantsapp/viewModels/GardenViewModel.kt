package com.example.plantsapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.plantsapp.data.Garden
import com.example.plantsapp.data.Plant
import com.example.plantsapp.data.repositories.GardenRepository
import com.example.plantsapp.utils.logger
import javax.inject.Inject

class GardenViewModel @Inject constructor(private val gardenRepository: GardenRepository): ViewModel() {

    private var _garden :MutableList<Plant> = mutableListOf()
    val garden : MutableList<Plant> get() = _garden
    fun clearGarden() = _garden.clear()

    suspend fun createGarden(gardenName: String) {

        gardenRepository.createGarden(_garden,gardenName)
    }
    private var _getAllGardensResponse = MutableLiveData<MutableList<Garden>?>(null)
    val getAllGardensResponse : MutableLiveData<MutableList<Garden>?> get() = _getAllGardensResponse
    fun _clearGetAllGardensResponse() = _garden.clear()

    suspend fun getAllGardens()
    {
        gardenRepository.getAllGardens().let {
            if (it.isNotEmpty())
            {
                _getAllGardensResponse.postValue(it)
                logger(it.toString())
            }
        }
    }

    private var _getGardenResponse = MutableLiveData<Garden?>(null)
    val getGardenResponse : MutableLiveData<Garden?> get() = _getGardenResponse
    fun _clearGetGardenResponse() = _getGardenResponse.postValue(null)

    suspend fun getGardenById(id:Int)
    {
        gardenRepository.getGardenById(id).let {
            _getGardenResponse.postValue(it)
        }
    }

    suspend fun addPlantToGarden(garden: Garden){
        gardenRepository.updateGarden(garden)
    }
}