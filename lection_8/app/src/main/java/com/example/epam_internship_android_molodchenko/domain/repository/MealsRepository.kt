package com.example.epam_internship_android_molodchenko.domain.repository

import com.example.epam_internship_android_molodchenko.data.model.meal.ModelMealList
import io.reactivex.Single

interface MealsRepository {

    fun loadMealsData(strCategory: String): Single<ModelMealList>
}