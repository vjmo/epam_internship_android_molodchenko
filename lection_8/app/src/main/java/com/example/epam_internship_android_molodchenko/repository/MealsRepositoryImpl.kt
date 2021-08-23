package com.example.epam_internship_android_molodchenko.repository

import com.example.epam_internship_android_molodchenko.api.MealApi
import com.example.epam_internship_android_molodchenko.models.ModelMealList
import io.reactivex.Single
import retrofit2.Call

class MealsRepositoryImpl(private val api: MealApi) : MealsRepository {

    override fun loadMealsData(strCategory: String): Single<ModelMealList> =
        api.getMeals(strCategory)
}