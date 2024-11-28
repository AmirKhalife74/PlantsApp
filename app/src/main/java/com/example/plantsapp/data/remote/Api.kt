package com.example.plantsapp.data.remote


import com.example.data.model.User
import com.example.data.model.auth.LoginRequest
import com.example.data.model.auth.RegisterRequest
import com.example.plantsapp.data.Plant
import com.example.plantsapp.data.model.ResponseModel
import com.example.plantsapp.data.model.auth.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {

    //Auth
    @POST("/login")
    suspend fun login(@Body loginRequest: LoginRequest):Response<ResponseModel<LoginResponse>>

    @POST("/register")
    suspend fun register(@Body registerRequest: RegisterRequest):Response<ResponseModel<User>>

    @POST("/refresh")
    suspend fun refresh(@Body refreshToken: String):Response<ResponseModel<User>>


    //GetPlantsData
    @GET("app/getAllPlants")
    suspend fun getAllPlants():Response<ResponseModel<List<Plant>>>

    @POST("app/getPlantById")
    suspend fun getPlantById(@Query("id")int: String):Response<ResponseModel<Plant>>


    // UserData
    @POST("getUserInfo")
    suspend fun getUserInfo():Response<ResponseModel<User>>

}