package com.example.epam_internship_android_molodchenko.repository

import com.example.epam_internship_android_molodchenko.models.ModelMealDetailsList
import retrofit2.Call

interface MealsDetailsRepository {

    fun loadDetailsData(idMeal: Int): Call<ModelMealDetailsList>
}