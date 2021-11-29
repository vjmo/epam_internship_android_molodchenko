package com.example.epam_internship_android_molodchenko.data.preferences

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferencesProvider @Inject constructor(private val sp: SharedPreferences) :
    IntSharedPreferences, StringSharedPreferences {

    override fun saveInt(key: String, value: Int) {
        sp
            .edit()
            .putInt(key, value)
            .apply()
    }

    override fun int(key: String, default: Int): Int {
        return sp.getInt(key, default)
    }

    override fun saveStr(key: String, value: String?) {
        sp
            .edit()
            .putString(key, value)
            .apply()
    }

    override fun str(key: String, default: String?): String? {
        return sp.getString(key, default) ?: default
    }
}