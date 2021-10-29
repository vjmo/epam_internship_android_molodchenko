package com.example.epam_internship_android_molodchenko.data.network


import com.example.epam_internship_android_molodchenko.data.model.meal.CategoryListDto
import com.example.epam_internship_android_molodchenko.data.model.mealDetails.MealDetailsListDto
import com.example.epam_internship_android_molodchenko.data.model.meal.MealListDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

interface MealApi {
    @GET("categories.php")
    fun getCategories(): Single<CategoryListDto>

    @GET("filter.php")
    fun getMeals(@Query("c") strCategory: String): Single<MealListDto>

    @GET("lookup.php")
    fun getDetails(@Query("i") idMeal: Int): Single<MealDetailsListDto>
}
//у меня здесь интерфейс, а не класс