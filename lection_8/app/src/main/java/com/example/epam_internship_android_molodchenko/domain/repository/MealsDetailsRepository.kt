package com.example.epam_internship_android_molodchenko.domain.repository

import com.example.epam_internship_android_molodchenko.data.model.mealDetails.ModelMealDetailsList
import io.reactivex.Single

interface MealsDetailsRepository {

    fun loadDetailsData(idMeal: Int): Single<ModelMealDetailsList>
}