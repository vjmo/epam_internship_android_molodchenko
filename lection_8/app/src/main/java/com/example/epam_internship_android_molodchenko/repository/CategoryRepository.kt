package com.example.epam_internship_android_molodchenko.repository

import com.example.epam_internship_android_molodchenko.models.ModelCategoryList
import retrofit2.Call

interface CategoryRepository {

    fun loadCategories(): Call<ModelCategoryList>

}