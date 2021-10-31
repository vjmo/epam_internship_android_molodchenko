package com.example.epam_internship_android_molodchenko.data.preferences

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferencesProvider @Inject constructor(private val sp: SharedPreferences) {

    fun setInt(lastIndex: Int){
        sp
            .edit()
            .putInt(KEY_CATEGORY, lastIndex)
            .apply()
    }

    fun getInt(): Int = sp.getInt(KEY_CATEGORY, 0)

    companion object {
        const val KEY_CATEGORY = "id_category"
    }
}