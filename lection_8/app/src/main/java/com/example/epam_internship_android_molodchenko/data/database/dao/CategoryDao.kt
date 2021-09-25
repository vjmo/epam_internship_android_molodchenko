package com.example.epam_internship_android_molodchenko.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.epam_internship_android_molodchenko.data.database.model.DbModelCategory
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category_data ORDER BY id")
    fun getCategoryDatabase(): Flowable<List<DbModelCategory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategoryDatabase(categoryDb: List<DbModelCategory>) : Completable
}