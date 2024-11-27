package com.example.data.model

import com.example.utils.UserRole

data class User(val id:Any?,
                val username: String,
                val email: String,
                val passwordHash: String,
                val role: UserRole,
                var refreshToken: String? = null
)