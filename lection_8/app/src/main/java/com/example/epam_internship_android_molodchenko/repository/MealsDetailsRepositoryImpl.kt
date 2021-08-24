package com.example.epam_internship_android_molodchenko.repository

import com.example.epam_internship_android_molodchenko.api.MealApi
import com.example.epam_internship_android_molodchenko.models.ModelMealDetailsList
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealsDetailsRepositoryImpl(private val api: MealApi) : MealsDetailsRepository {

    override fun loadDetailsData(idMeal: Int): Single<ModelMealDetailsList> =
        api.getDetails(idMeal)
}