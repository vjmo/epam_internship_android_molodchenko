package com.example.epam_internship_android_molodchenko.repository

import com.example.epam_internship_android_molodchenko.models.ModelMealList
import retrofit2.Call

interface MealsRepository {

    fun loadMealsData(strCategory: String): Call<ModelMealList>

}