package com.example.plantsapp.data.model

import java.io.Serializable


data class ResponseModel<T>(
    var status: Int,
    var isSuccessful:Boolean,
    var message: String,
    var data: T?
):Serializable