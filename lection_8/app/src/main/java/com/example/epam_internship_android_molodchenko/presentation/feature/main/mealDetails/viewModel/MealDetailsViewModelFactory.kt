package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealDetails.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.epam_internship_android_molodchenko.domain.useCase.GetMealDetailsUseCase

class MealDetailsViewModelFactory(private val mealDetailsUseCase: GetMealDetailsUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MealDetailsViewModel(mealDetailsUseCase) as T
    }
}