package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.viewModel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.epam_internship_android_molodchenko.domain.useCase.GetCategoryUseCase
import com.example.epam_internship_android_molodchenko.domain.useCase.RequestCategoryUseCase

class CategoryViewModelFactory(
    private val categoryUseCase: GetCategoryUseCase,
    private val categoryRequestUseCase: RequestCategoryUseCase,
    private val sharedPreferences: SharedPreferences) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CategoryViewModel(categoryUseCase, categoryRequestUseCase, sharedPreferences) as T
    }
}