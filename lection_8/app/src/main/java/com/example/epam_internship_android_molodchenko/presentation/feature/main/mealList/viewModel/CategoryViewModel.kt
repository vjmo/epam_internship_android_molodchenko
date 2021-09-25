package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.viewModel

import androidx.lifecycle.ViewModel
import com.example.epam_internship_android_molodchenko.domain.useCase.GetCategoryUseCase
import com.example.epam_internship_android_molodchenko.domain.useCase.RequestCategoryUseCase

class CategoryViewModel(
    private val categoryUseCase: GetCategoryUseCase,
    private val categoryRequestUseCase: RequestCategoryUseCase
) : ViewModel() {
}