package com.example.epam_internship_android_molodchenko.data.repository

import com.example.epam_internship_android_molodchenko.data.network.MealApi
import com.example.epam_internship_android_molodchenko.domain.entity.MealDetailsEntity
import com.example.epam_internship_android_molodchenko.domain.repository.MealsDetailsRepository
import com.example.epam_internship_android_molodchenko.data.mapper.toMealDetailsEntity
import io.reactivex.Single
import javax.inject.Inject

class MealsDetailsRepositoryImpl @Inject constructor(private val api: MealApi) :
    MealsDetailsRepository {

    override fun loadDetailsData(idMeal: Int): Single<List<MealDetailsEntity>> =
        api.getDetails(idMeal)
            .map { modelDetails -> modelDetails.mealsDetails.map { it.toMealDetailsEntity() } }
}