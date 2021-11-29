package com.example.epam_internship_android_molodchenko.data.preferences

interface StringSharedPreferences {

    fun saveStr(key: String, value: String?)

    fun str(key: String, default: String?): String?
}