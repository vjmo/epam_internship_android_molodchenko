package com.example.epam_internship_android_molodchenko.data

import androidx.room.Dao
import androidx.room.Query
import com.example.epam_internship_android_molodchenko.models.ModelCategory
import io.reactivex.Single

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category_data ORDER BY id")
    fun getCategoryDatabase(): Single<List<ModelCategory>>
}