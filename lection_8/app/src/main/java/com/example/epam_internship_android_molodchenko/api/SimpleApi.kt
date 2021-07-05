package com.example.epam_internship_android_molodchenko.api


import com.example.epam_internship_android_molodchenko.ModelCategory
import com.example.epam_internship_android_molodchenko.ModelCategoryList
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.OPTIONS
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface SimpleApi {
    @GET("categories.php")
    suspend fun getCategories(): ModelCategory

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