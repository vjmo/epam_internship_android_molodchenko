package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.viewModel

import androidx.lifecycle.ViewModel
import com.example.epam_internship_android_molodchenko.domain.useCase.GetMealListUseCase

class MealViewModel(private val mealUseCase: GetMealListUseCase) : ViewModel() {
}