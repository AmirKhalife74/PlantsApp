package com.example.plantsapp.data.model.auth

import com.example.utils.UserRole
import java.io.Serializable

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String,
    val confirmPassword: String? = null,
    val role: UserRole = UserRole.USER
):Serializable
