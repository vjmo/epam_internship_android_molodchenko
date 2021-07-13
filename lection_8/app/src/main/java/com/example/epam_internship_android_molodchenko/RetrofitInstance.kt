package com.example.epam_internship_android_molodchenko

import com.example.epam_internship_android_molodchenko.repository.Constants.Companion.BASE_URL
import com.example.epam_internship_android_molodchenko.api.MealApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private var retrofit: Retrofit? = null
    fun getData(baseUrl: String): Retrofit{
        if(retrofit == null)
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit!!
    }
   /* private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val mealApi: MealApi by lazy {
        retrofit.create(MealApi::class.java)
    }*/
}