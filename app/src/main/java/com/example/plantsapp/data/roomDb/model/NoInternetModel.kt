package com.example.plantsapp.data.roomDb.model

data class NoInternetModel(
    val status: Boolean?,
    val message: String="دسترسی به اینترنت موجود نیست",
    val function: ()->Unit
)