package com.example.plantsapp.data.model.auth

import java.io.Serializable

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String
):Serializable
