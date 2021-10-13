package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.viewModel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.epam_internship_android_molodchenko.domain.useCase.GetCategoryUseCase
import com.example.epam_internship_android_molodchenko.domain.useCase.GetMealListUseCase
import com.example.epam_internship_android_molodchenko.domain.useCase.RequestCategoryUseCase

class MealViewModelFactory(
    private val mealUseCase: GetMealListUseCase,
    private val categoryUseCase: GetCategoryUseCase,
    private val categoryRequestUseCase: RequestCategoryUseCase,
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MealViewModel(mealUseCase, categoryUseCase, categoryRequestUseCase) as T
    }
}