package com.example.epam_internship_android_molodchenko.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.epam_internship_android_molodchenko.models.ModelCategory

@Database(entities = [ModelCategory::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getCategoryDao(): CategoryDao
}