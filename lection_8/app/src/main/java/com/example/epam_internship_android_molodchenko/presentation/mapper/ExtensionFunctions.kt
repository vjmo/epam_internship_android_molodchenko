package com.example.epam_internship_android_molodchenko.presentation.mapper

import com.example.epam_internship_android_molodchenko.domain.entity.CategoryEntity
import com.example.epam_internship_android_molodchenko.domain.entity.MealDetailsEntity
import com.example.epam_internship_android_molodchenko.domain.entity.MealEntity
import com.example.epam_internship_android_molodchenko.presentation.model.CategoryUIModel
import com.example.epam_internship_android_molodchenko.presentation.model.MealDetailsUIModel
import com.example.epam_internship_android_molodchenko.presentation.model.MealUIModel

fun CategoryEntity.toCategoryUIModel() =
    CategoryUIModel(id, titleCategory, imageCategory, descriptionCategory)

fun MealEntity.toMealUIModel() =
    MealUIModel(id, titleMeal, imageMeal)

fun MealDetailsEntity.toMealDetailsUIModel() =
    MealDetailsUIModel(id, titleMeal, areaMeal, tagMeal, ingredientsMeal, thumbMeal, youtubeUrlMeal)