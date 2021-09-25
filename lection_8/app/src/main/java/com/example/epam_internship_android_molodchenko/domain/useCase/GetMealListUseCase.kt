package com.example.epam_internship_android_molodchenko.domain.useCase

import com.example.epam_internship_android_molodchenko.domain.entity.MealEntity
import com.example.epam_internship_android_molodchenko.domain.repository.MealsDetailsRepository
import com.example.epam_internship_android_molodchenko.domain.repository.MealsRepository
import io.reactivex.Single

class GetMealListUseCase(private val mealsRepository: MealsRepository) {
    operator fun invoke(strCategory: String): Single<MealEntity> =
        mealsRepository.loadMealsData(strCategory)
}