package com.example.epam_internship_android_molodchenko.data.repository

import com.example.epam_internship_android_molodchenko.data.network.MealApi
import com.example.epam_internship_android_molodchenko.data.model.meal.ModelMealListDto
import com.example.epam_internship_android_molodchenko.domain.entity.MealEntity
import com.example.epam_internship_android_molodchenko.domain.repository.MealsRepository
import io.reactivex.Single

class MealsRepositoryImpl(private val api: MealApi) : MealsRepository {

    override fun loadMealsData(strCategory: String): Single<ModelMealListDto> =
        api.getMeals(strCategory)
}