package com.example.epam_internship_android_molodchenko.domain.repository

import com.example.epam_internship_android_molodchenko.data.model.mealDetails.ModelMealDetailsListDto
import com.example.epam_internship_android_molodchenko.domain.entity.MealDetailsEntity
import com.example.epam_internship_android_molodchenko.domain.entity.MealEntity
import io.reactivex.Single

interface MealsDetailsRepository {

    fun loadDetailsData(idMeal: Int): Single<MealDetailsEntity>
}