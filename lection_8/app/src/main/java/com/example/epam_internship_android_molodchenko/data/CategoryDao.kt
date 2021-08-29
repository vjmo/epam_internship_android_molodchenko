package com.example.epam_internship_android_molodchenko.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.epam_internship_android_molodchenko.models.ModelCategory
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category_data ORDER BY id")
    fun getCategoryDatabase(): Single<MutableList<ModelCategory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategoryDatabase(category: MutableList<ModelCategory>): Completable
}