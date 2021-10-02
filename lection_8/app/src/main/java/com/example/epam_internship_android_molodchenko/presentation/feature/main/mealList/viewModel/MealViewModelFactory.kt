package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.viewModel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.epam_internship_android_molodchenko.domain.useCase.GetMealListUseCase

class MealViewModelFactory(
    private val mealUseCase: GetMealListUseCase,
    private val sharedPreferences: SharedPreferences,
    private val context: Context
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MealViewModel(mealUseCase, sharedPreferences, context) as T
    }
}