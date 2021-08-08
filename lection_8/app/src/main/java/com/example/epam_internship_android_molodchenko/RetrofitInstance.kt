package com.example.epam_internship_android_molodchenko

import com.example.epam_internship_android_molodchenko.repository.Constants.Companion.BASE_URL
import com.example.epam_internship_android_molodchenko.api.MealApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    val mealApi: MealApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return@lazy retrofit.create(MealApi::class.java)
    }
}