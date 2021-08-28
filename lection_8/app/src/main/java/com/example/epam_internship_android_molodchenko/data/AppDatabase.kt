package com.example.epam_internship_android_molodchenko.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.epam_internship_android_molodchenko.models.ModelCategory

@Database(entities = [ModelCategory::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getCategoryDao(): CategoryDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "category_database"
                ).build()
            }
            return INSTANCE as AppDatabase
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}