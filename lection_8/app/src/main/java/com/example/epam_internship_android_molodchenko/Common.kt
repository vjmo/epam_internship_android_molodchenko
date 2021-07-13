package com.example.epam_internship_android_molodchenko

import com.example.epam_internship_android_molodchenko.api.MealApi
import com.example.epam_internship_android_molodchenko.repository.Constants
object Common {
    private val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    val mealApi: MealApi
    get() = RetrofitInstance.getData(BASE_URL).create(MealApi::class.java)
}