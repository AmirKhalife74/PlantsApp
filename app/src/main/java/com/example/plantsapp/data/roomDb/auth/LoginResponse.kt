package com.example.plantsapp.data.roomDb.auth

import java.io.Serializable

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String
):Serializable
