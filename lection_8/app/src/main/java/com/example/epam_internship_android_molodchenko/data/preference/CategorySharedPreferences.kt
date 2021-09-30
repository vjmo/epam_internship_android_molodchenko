package com.example.epam_internship_android_molodchenko.data.preference

import android.content.Context
import android.content.SharedPreferences

class CategorySharedPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("settings_prefs", Context.MODE_PRIVATE)
}