package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view.clickListener

import com.example.epam_internship_android_molodchenko.data.model.meal.ModelMeal

interface OnItemClickListenerMeal {
    fun onItemClick(meal: ModelMeal)
}