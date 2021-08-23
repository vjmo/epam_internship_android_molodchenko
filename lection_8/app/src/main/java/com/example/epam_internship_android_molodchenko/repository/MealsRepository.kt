package com.example.epam_internship_android_molodchenko.repository

import com.example.epam_internship_android_molodchenko.models.ModelMealList
import io.reactivex.Single
import retrofit2.Call

interface MealsRepository {

    fun loadMealsData(strCategory: String): Single<ModelMealList>
}