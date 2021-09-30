package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view.clickListener

import com.example.epam_internship_android_molodchenko.data.model.meal.ModelMealDto
import com.example.epam_internship_android_molodchenko.presentation.model.MealUIModel

interface OnItemClickListenerMeal {
    fun onItemClick(mealUI: MealUIModel)
}