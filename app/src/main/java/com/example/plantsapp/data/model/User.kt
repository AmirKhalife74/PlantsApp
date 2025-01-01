package com.example.plantsapp.data.model

import com.example.utils.UserRole
import java.io.Serializable

data class User(val id: String,
                val username: String,
                val email: String,
                val passwordHash: String,
                val role: UserRole,
                val imageProfileAddress:String,
                var refreshToken: String? = null
): Serializable
