package com.example.epam_internship_android_molodchenko.api


import com.example.epam_internship_android_molodchenko.models.ModelCategoryList
import com.example.epam_internship_android_molodchenko.models.ModelMealDetailsList
import com.example.epam_internship_android_molodchenko.models.ModelMealList
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("categories.php")
    fun getCategories(): Single<ModelCategoryList>

    @GET("filter.php")
    fun getMeals(@Query("c") strCategory: String): Single<ModelMealList>

    @GET("lookup.php")
    fun getDetails(@Query("i") idMeal: Int): Single<ModelMealDetailsList>
}