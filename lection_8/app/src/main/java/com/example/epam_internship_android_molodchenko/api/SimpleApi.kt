package com.example.epam_internship_android_molodchenko.api


import com.example.epam_internship_android_molodchenko.ModelCategoryList
import retrofit2.Call
import retrofit2.http.GET

interface SimpleApi {
    @GET("categories.php")
    suspend fun getCategories(): Call<ModelCategoryList>
}