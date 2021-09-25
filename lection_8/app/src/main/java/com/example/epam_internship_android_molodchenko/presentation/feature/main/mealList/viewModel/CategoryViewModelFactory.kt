package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.epam_internship_android_molodchenko.domain.useCase.GetCategoryUseCase

class CategoryViewModelFactory(private val categoryUseCase: GetCategoryUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CategoryViewModel(categoryUseCase) as T
    }
}