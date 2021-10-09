package com.example.epam_internship_android_molodchenko

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.room.Room
import com.example.epam_internship_android_molodchenko.data.database.AppDatabase

class TestApp : Application() {

    val db: AppDatabase by lazy {
        Room.databaseBuilder(this, AppDatabase::class.java, DB_NAME).build()
    }

    val sp: SharedPreferences by lazy {
        this.getSharedPreferences(// я хз насчет this
            "settings_prefs",
            Context.MODE_PRIVATE
        )
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        private val DB_NAME = "meal"
        lateinit var INSTANCE: TestApp
    }

}