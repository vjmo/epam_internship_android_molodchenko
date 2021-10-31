package com.example.epam_internship_android_molodchenko.di.module

import android.content.Context
import android.content.SharedPreferences
import com.example.epam_internship_android_molodchenko.data.preferences.SharedPreferencesProvider
import com.example.epam_internship_android_molodchenko.di.component.App.Companion.SH_NAME
import dagger.Module
import dagger.Provides

@Module
class SharedPreferencesModule {

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(SH_NAME, Context.MODE_PRIVATE)

    @Provides
    fun provideCategorySharedPreferences(sp: SharedPreferences) = SharedPreferencesProvider(sp)
}