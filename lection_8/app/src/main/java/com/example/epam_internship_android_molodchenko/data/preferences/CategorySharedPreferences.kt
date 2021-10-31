package com.example.epam_internship_android_molodchenko.data.preferences

import android.content.SharedPreferences
import javax.inject.Inject

class CategorySharedPreferences @Inject constructor(private val sp: SharedPreferences) {

    fun setLastCategory(categoryInt: Int){
        sp
            .edit()
            .putInt(KEY_CATEGORY, categoryInt)
            .apply()
    }

    fun getLastCategory(): Int = sp.getInt(KEY_CATEGORY, 0)

    companion object {
        const val KEY_CATEGORY = "id_category"
    }
}