package com.example.plantsapp.utils

import android.content.Context

class Presfs(context: Context) {
    private val sharedPref = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)

    fun setFirstLogin() {
        val editor = sharedPref.edit()
        editor.putString("first_login", "true")
        editor.apply()
    }

    fun isFirstLogin(): String {
        return (sharedPref.getString("first_login", "false") ?: "false")
    }

    fun loadUserAge(): Int {
        return sharedPref.getInt("age", -1)
    }
}