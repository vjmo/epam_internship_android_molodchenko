package com.example.epam_internship_android_molodchenko.data.network


import com.example.epam_internship_android_molodchenko.data.model.meal.ModelCategoryListDto
import com.example.epam_internship_android_molodchenko.data.model.mealDetails.ModelMealDetailsListDto
import com.example.epam_internship_android_molodchenko.data.model.meal.ModelMealListDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("categoryDbs.php")
    fun getCategories(): Single<ModelCategoryListDto>

    @GET("filter.php")
    fun getMeals(@Query("c") strCategory: String): Single<ModelMealListDto>

    @GET("lookup.php")
    fun getDetails(@Query("i") idMeal: Int): Single<ModelMealDetailsListDto>
}