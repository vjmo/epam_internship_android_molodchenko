package com.example.epam_internship_android_molodchenko.domain.useCase

import com.example.epam_internship_android_molodchenko.domain.entity.MealDetailsEntity
import com.example.epam_internship_android_molodchenko.domain.repository.CategoryRepository
import com.example.epam_internship_android_molodchenko.domain.repository.MealsDetailsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetMealDetailsUseCase @Inject constructor(private val mealsDetailsRepository: MealsDetailsRepository) {
    operator fun invoke(idMeal: Int): Single<List<MealDetailsEntity>> =
        mealsDetailsRepository.loadDetailsData(idMeal)
}