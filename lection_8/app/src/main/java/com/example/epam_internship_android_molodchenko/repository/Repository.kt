package com.example.epam_internship_android_molodchenko.repository

import com.example.epam_internship_android_molodchenko.ModelCategoryList
import com.example.epam_internship_android_molodchenko.RetrofitInstance
import retrofit2.Call

class Repository {
    suspend fun getCategories(): Call<ModelCategoryList> {
        RetrofitInstance.api.getCategories()
    }
}