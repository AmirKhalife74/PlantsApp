package com.example.plantsapp.Data.Remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.plantsapp.Data.Plant
import retrofit2.http.GET

interface Api {

    @GET("plants")
    fun getAllPlants():LiveData<List<Plant>>

}