package com.example.epam_internship_android_molodchenko.api


import com.example.epam_internship_android_molodchenko.models.ModelCategory
import com.example.epam_internship_android_molodchenko.models.ModelCategoryList
import com.example.epam_internship_android_molodchenko.models.ModelMealDetailsList
import com.example.epam_internship_android_molodchenko.models.ModelMealList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MealApi {
    @GET("categories.php")
    suspend fun getCategories(): Call<ModelCategoryList>

    @GET("filter.php")
    suspend fun getMeals(@Query("c") strCategory: String): Call<ModelMealList>

    @GET("lookup.php")
    suspend fun getDetails(@Query("i") idMeal: Int): Call<ModelMealDetailsList>
}