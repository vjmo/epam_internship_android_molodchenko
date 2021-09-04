package com.example.epam_internship_android_molodchenko

import android.app.Application
import androidx.room.Room
import com.example.epam_internship_android_molodchenko.data.AppDatabase

class TestApp : Application() {

    val db: AppDatabase by lazy {
        Room.databaseBuilder(this, AppDatabase::class.java, DB_NAME).build()
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