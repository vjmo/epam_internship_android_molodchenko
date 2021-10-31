package com.example.epam_internship_android_molodchenko.data.repository

import com.example.epam_internship_android_molodchenko.data.network.MealApi
import com.example.epam_internship_android_molodchenko.domain.entity.MealEntity
import com.example.epam_internship_android_molodchenko.domain.repository.MealsRepository
import com.example.epam_internship_android_molodchenko.exten_fun.toMealEntity
import io.reactivex.Single
import javax.inject.Inject

class MealsRepositoryImpl @Inject constructor(private val api: MealApi) : MealsRepository {

    override fun loadMealsData(strCategory: String): Single<List<MealEntity>> =
        api.getMeals(strCategory)
            .map { modelMeal ->
                modelMeal.mealDtos
                    .map { it.toMealEntity() }
            }
}