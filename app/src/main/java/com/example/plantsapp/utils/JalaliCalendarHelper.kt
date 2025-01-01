package com.example.plantsapp.utils

import java.util.*

class JalaliDate {
    private val gregorianDaysInMonth = intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    private val jalaliDaysInMonth = intArrayOf(31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29)

    fun gregorianToJalali(year: Int, month: Int, day: Int): Triple<Int, Int, Int> {
        val gy = year - 1600
        val gm = month - 1
        var gd = day - 1

        val gDayNo = 365 * gy + ((gy + 3) / 4) - ((gy + 99) / 100) + ((gy + 399) / 400)
        for (i in 0 until gm) {
            gd += gregorianDaysInMonth[i]
        }
        if (gm > 1 && isLeapGregorian(year)) {
            gd += 1
        }
        gd += gDayNo

        val jDayNo = gd - 79
        val jNp = jDayNo / 12053
        val jp = jDayNo % 12053
        var jy = 979 + 33 * jNp + 4 * (jp / 1461)
        val r = jp % 1461

        val jMonth: Int
        val jDay: Int
        if (r >= 366) {
            jy += (r - 1) / 365
            val rDayNo = (r - 1) % 365
            for (i in 0 until 11) {
                if (rDayNo < jalaliDaysInMonth[i]) {
                    jMonth = i + 1
                    jDay = rDayNo + 1
                    return Triple(jy, jMonth, jDay)
                }
            }
        }

        jMonth = 1
        jDay = r + 1
        return Triple(jy, jMonth, jDay)
    }
    fun jalaliToGregorian(year: Int, month: Int, day: Int): Triple<Int, Int, Int> {
        val jalaliDaysInMonth = intArrayOf(31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29)

        val jy = year - 979
        val jm = month - 1
        var jd = day - 1

        val jDayNo = 365 * jy + jy / 33 * 8 + (jy % 33 + 3) / 4
        for (i in 0 until jm) {
            jd += jalaliDaysInMonth[i]
        }
        jd += jDayNo

        val gDayNo = jd + 79
        var gy = 1600 + 400 * (gDayNo / 146097)
        var gDayR = gDayNo % 146097

        var leap = true
        if (gDayR >= 36525) {
            gDayR--
            gy += 100 * (gDayR / 36524)
            gDayR %= 36524

            if (gDayR >= 365) {
                gDayR++
            } else {
                leap = false
            }
        }

        gy += 4 * (gDayR / 1461)
        gDayR %= 1461

        if (gDayR >= 366) {
            leap = false
            gDayR--
            gy += gDayR / 365
            gDayR %= 365
        }

        val gm: Int
        val gd: Int
        val gregorianDaysInMonth = intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

        for (i in 0 until 12) {
            val daysInMonth = if (i == 1 && leap) 29 else gregorianDaysInMonth[i]
            if (gDayR < daysInMonth) {
                gm = i + 1
                gd = gDayR + 1
                return Triple(gy, gm, gd)
            }
            gDayR -= daysInMonth
        }

        gm = 1
        gd = gDayR + 1
        return Triple(gy, gm, gd)
    }


    private fun isLeapGregorian(year: Int): Boolean {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    }

    fun getDaysInJalaliMonth(year: Int, month: Int): Int {
        return if (month == 12 && isLeapJalali(year)) 30 else jalaliDaysInMonth[month - 1]
    }

    private fun isLeapJalali(year: Int): Boolean {
        val a = (year - 474) % 2820 + 474
        return ((a + 38) * 682 % 2816) < 682
    }

    fun getStartDayOfMonth(year: Int, month: Int): Int {
        val gregorianCalendar = Calendar.getInstance()
        val (gYear, gMonth, gDay) = jalaliToGregorian(year, month, 1)
        gregorianCalendar.set(gYear, gMonth - 1, gDay)
        return gregorianCalendar.get(Calendar.DAY_OF_WEEK) // 1 = Sunday, ..., 7 = Saturday
    }
    fun getMonthName(month: Int): String {
        val monthNames = arrayOf(
            "Farvardin", "Ordibehesht", "Khordad", "Tir", "Mordad", "Shahrivar",
            "Mehr", "Aban", "Azar", "Dey", "Bahman", "Esfand"
        )
        return if (month in 1..12) {
            monthNames[month - 1]
        } else {
            "Invalid Month"
        }
    }

}
