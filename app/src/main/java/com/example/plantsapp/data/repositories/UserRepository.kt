package com.example.plantsapp.data.repositories

import android.content.Context
import com.example.plantsapp.data.roomDb.user.User
import com.example.plantsapp.data.model.auth.LoginRequest
import com.example.plantsapp.data.model.auth.RegisterRequest
import com.example.plantsapp.data.model.ResponseModel
import com.example.plantsapp.data.remote.Api
import com.example.plantsapp.data.roomDb.DAO
import com.example.plantsapp.data.model.auth.LoginResponse
import retrofit2.Response

class UserRepository(private val dao: DAO, private val context: Context, private val api: Api) {

    suspend fun login(loginRequest: LoginRequest): Response<ResponseModel<LoginResponse>> {
        return api.login(loginRequest)
    }

    suspend fun register(registerRequest: RegisterRequest): Response<ResponseModel<User>> {
        return api.register(registerRequest)
    }
}