package com.example.epam_internship_android_molodchenko.domain.repository

import com.example.epam_internship_android_molodchenko.data.model.meal.ModelMealListDto
import com.example.epam_internship_android_molodchenko.domain.entity.MealEntity
import io.reactivex.Single

interface MealsRepository {

    fun loadMealsData(strCategory: String): Single<MealEntity>
}