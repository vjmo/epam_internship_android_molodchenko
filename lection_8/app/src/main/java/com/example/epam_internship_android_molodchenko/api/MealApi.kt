package com.example.epam_internship_android_molodchenko.api


import com.example.epam_internship_android_molodchenko.models.ModelCategory
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MealApi {
    @GET("categories.php")
    suspend fun getCategories(): Call<List<ModelCategory>>

    @GET("filter.php")
    suspend fun getMeals(): Call<List<ModelCategory>>

    @GET("categories")
    suspend fun getCustomCategory(
        @Query("idCategory") idCategory: Int,
        @Query("strCategory") strCategory: String,
        @Query("strCategoryThumb") strCategoryThumb: String,
        @Query("strCategoryDescription") strCategoryDescription: String
    ): Response<List<ModelCategory>>

    @GET("categories")
    suspend fun getCustomCategoryTwo(
        @Query("idCategory") idCategory: Int,
        @QueryMap options: Map<String, String>
    ): Response<List<ModelCategory>>
}