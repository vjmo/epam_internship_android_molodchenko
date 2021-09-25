package com.example.epam_internship_android_molodchenko.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.epam_internship_android_molodchenko.data.database.dao.CategoryDao
import com.example.epam_internship_android_molodchenko.data.database.model.DbModelCategory

@Database(
    entities = [DbModelCategory::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getCategoryDao(): CategoryDao
}