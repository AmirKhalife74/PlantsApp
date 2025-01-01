package com.example.plantsapp.data.model.auth

import java.io.Serializable

data class LoginRequest(
val username: String,
val password: String):Serializable
