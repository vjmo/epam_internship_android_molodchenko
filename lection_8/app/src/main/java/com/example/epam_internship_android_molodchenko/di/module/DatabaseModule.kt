package com.example.epam_internship_android_molodchenko.di.module

import android.content.Context
import androidx.room.Room
import com.example.epam_internship_android_molodchenko.data.database.AppDatabase
import com.example.epam_internship_android_molodchenko.data.database.dao.CategoryDao
import com.example.epam_internship_android_molodchenko.di.component.App
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideAppDatabase(context: Context): AppDatabase = Room.databaseBuilder(
        context, AppDatabase::class.java,
        App.DB_NAME
    ).build()

    @Provides
    fun provideCategoryDao(appDatabase: AppDatabase): CategoryDao = appDatabase.getCategoryDao()
}