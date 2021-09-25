package com.example.epam_internship_android_molodchenko.domain.entity

data class MealDetailsEntity(
    val id: Int,
    val titleMeal: String,
    val areaMeal: String,
    val tagMeal: MutableList<String>,
    val ingredientsMeal: String,
    val thumbMeal: String,
    val youtubeUrlMeal: String?
)
