package com.example.epam_internship_android_molodchenko.data.repository

import com.example.epam_internship_android_molodchenko.data.network.MealApi
import com.example.epam_internship_android_molodchenko.data.model.mealDetails.ModelMealDetailsList
import com.example.epam_internship_android_molodchenko.domain.repository.MealsDetailsRepository
import io.reactivex.Single

class MealsDetailsRepositoryImpl(private val api: MealApi) : MealsDetailsRepository {

    override fun loadDetailsData(idMeal: Int): Single<ModelMealDetailsList> =
        api.getDetails(idMeal)
}