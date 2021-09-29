package com.example.epam_internship_android_molodchenko.data.repository

import com.example.epam_internship_android_molodchenko.data.network.MealApi
import com.example.epam_internship_android_molodchenko.data.model.mealDetails.ModelMealDetailsListDto
import com.example.epam_internship_android_molodchenko.domain.entity.MealDetailsEntity
import com.example.epam_internship_android_molodchenko.domain.repository.MealsDetailsRepository
import com.example.epam_internship_android_molodchenko.exten_fun.toMealDetailsEntity
import io.reactivex.Single

class MealsDetailsRepositoryImpl(private val api: MealApi) : MealsDetailsRepository {

    override fun loadDetailsData(idMeal: Int): Single<List<MealDetailsEntity>> =
        api.getDetails(idMeal)
            .map { modelDetails -> modelDetails.mealsDetails.map { it.toMealDetailsEntity() } }
}