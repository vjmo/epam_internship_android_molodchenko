package com.example.epam_internship_android_molodchenko.repository

import com.example.epam_internship_android_molodchenko.ModelCategory
import com.example.epam_internship_android_molodchenko.ModelCategoryList
import com.example.epam_internship_android_molodchenko.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class Repository {
    suspend fun getCategories(): ModelCategory {
        return RetrofitInstance.api.getCategories()
    }

    suspend fun getCustomCategory(): Response<List<ModelCategory>> {
        return RetrofitInstance.api.getCustomCategory()
    }
}