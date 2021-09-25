package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.viewModel

import androidx.lifecycle.ViewModel
import com.example.epam_internship_android_molodchenko.domain.useCase.GetCategoryUseCase

class CategoryViewModel(private val categoryUseCase: GetCategoryUseCase) : ViewModel() {
}