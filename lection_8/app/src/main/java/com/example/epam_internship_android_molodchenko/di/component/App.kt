package com.example.epam_internship_android_molodchenko.di.component

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.epam_internship_android_molodchenko.data.database.AppDatabase

class App : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.factory().create(this)
        INSTANCE = this
    }

    companion object {

        lateinit var INSTANCE: App

        internal const val DB_NAME = "meal"
        internal const val SH_NAME = "settings_prefs"
    }

}