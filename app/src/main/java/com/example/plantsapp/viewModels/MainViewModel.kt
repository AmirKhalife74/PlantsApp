package com.example.plantsapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.plantsapp.data.Plant
import com.example.plantsapp.data.model.ResponseModel
import com.example.plantsapp.data.roomDb.model.Cart
import com.example.plantsapp.data.repositories.PlantsRepository
import javax.inject.Inject


class MainViewModel @Inject constructor(private val plantsRepository: PlantsRepository) : ViewModel() {



    private var _plantList = MutableLiveData<ResponseModel<List<Plant>>?>(null)
    val plantList : MutableLiveData<ResponseModel<List<Plant>>?> get() = _plantList
    fun clearPlantsList() = _plantList.postValue(null)

    private var _cart = MutableLiveData<Cart?>(null)
    val cart : MutableLiveData<Cart?> get() = _cart
    fun clearCart() = _cart.postValue(null)

    suspend fun getPlants(){
        plantList.postValue(plantsRepository.getAllPlants())
    }

    suspend fun getPlantById(name: String){
        plantsRepository.getPlantsByName(name)

    }

    suspend fun insertPlant(plant: Plant){
        plantsRepository.insertPlant(plant)
    }

    suspend fun deletePlant(plant: Plant){
        plantsRepository.deletePlant(plant)
    }

}