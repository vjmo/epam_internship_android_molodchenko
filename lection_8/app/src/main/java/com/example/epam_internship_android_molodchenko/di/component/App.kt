package com.example.epam_internship_android_molodchenko.di.component

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.epam_internship_android_molodchenko.data.database.AppDatabase

class App : Application() {

    lateinit var component: AppComponent

    val db: AppDatabase by lazy {
        Room.databaseBuilder(this, AppDatabase::class.java, DB_NAME).build()
    }

    val sp: SharedPreferences by lazy {
        this.getSharedPreferences(
            SH_NAME,
            Context.MODE_PRIVATE
        )
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.factory().create(this)
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: App

        private const val DB_NAME = "meal"
        private const val SH_NAME = "settings_prefs"
    }

}