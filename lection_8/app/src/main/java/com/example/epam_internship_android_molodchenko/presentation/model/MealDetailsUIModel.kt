package com.example.epam_internship_android_molodchenko.presentation.model

data class MealDetailsUIModel(
    val idMealDetails: Int,
    val nameMealDetails: String,
    val area: String,
    val tags: MutableList<String>,
    val ingredients: String,
    val mealThumb: String,
    val youtube: String?
)