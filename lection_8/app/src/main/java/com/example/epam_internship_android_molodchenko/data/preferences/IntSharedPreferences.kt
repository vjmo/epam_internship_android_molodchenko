package com.example.epam_internship_android_molodchenko.data.preferences

interface IntSharedPreferences {

    fun saveInt(key: String, value: Int)

    fun int(key: String, default: Int) : Int
}