package com.example.plantsapp.Data.RoomDb.model

data class NoInternetModel(
    val status: Boolean?,
    val message: String="دسترسی به اینترنت موجود نیست",
    val function: ()->Unit
)