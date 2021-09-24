package com.example.epam_internship_android_molodchenko.data.repository

import com.example.epam_internship_android_molodchenko.data.network.MealApi
import com.example.epam_internship_android_molodchenko.models.ModelMealList
import com.example.epam_internship_android_molodchenko.domain.repository.MealsRepository
import io.reactivex.Single

class MealsRepositoryImpl(private val api: MealApi) : MealsRepository {

    override fun loadMealsData(strCategory: String): Single<ModelMealList> =
        api.getMeals(strCategory)
}