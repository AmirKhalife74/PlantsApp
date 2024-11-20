package com.example.plantsapp.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.plantsapp.Data.Plant
import com.example.plantsapp.Data.RoomDb.model.Cart
import com.example.plantsapp.Repositories.PlantsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class MainViewModel @Inject constructor(private val plantsRepository: PlantsRepository) : ViewModel() {



    private var _plantList = MutableLiveData<MutableList<Plant?>>(null)
    val plantList : MutableLiveData<MutableList<Plant?>> get() = _plantList
    fun clearPlantsList() = _plantList.postValue(mutableListOf(null))

    private var _cart = MutableLiveData<Cart?>(null)
    val cart : MutableLiveData<Cart?> get() = _cart
    fun clearCart() = _cart.postValue(null)

    suspend fun getPlants(){
        plantList.postValue(plantsRepository.getAllPlants().toMutableList())
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