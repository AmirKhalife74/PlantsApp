package com.example.plantsapp.Utils

// DateUtil.kt
object DateUtil {
    // Function to get the number of days in a given Persian month
    fun getDaysInMonth(year: Int, month: Int): Int {
        return when (month) {
            1, 2, 3, 4, 5, 6 -> 31
            7, 8, 9, 10, 11 -> 30
            12 -> if (isLeapYear(year)) 30 else 29
            else -> throw IllegalArgumentException("Invalid month")
        }
    }

    // Check if a given Persian year is a leap year
    fun isLeapYear(year: Int): Boolean {
        val modYear = year % 33
        return modYear == 1 || modYear == 5 || modYear == 9 || modYear == 13 || modYear == 17 ||
                modYear == 22 || modYear == 26 || modYear == 30
    }
}
