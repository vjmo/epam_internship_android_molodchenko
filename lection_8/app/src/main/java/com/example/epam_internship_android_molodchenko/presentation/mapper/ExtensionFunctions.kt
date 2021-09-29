package com.example.epam_internship_android_molodchenko.presentation.mapper

import com.example.epam_internship_android_molodchenko.domain.entity.CategoryEntity
import com.example.epam_internship_android_molodchenko.domain.entity.MealDetailsEntity
import com.example.epam_internship_android_molodchenko.domain.entity.MealEntity
import com.example.epam_internship_android_molodchenko.presentation.model.CategoryUIModel
import com.example.epam_internship_android_molodchenko.presentation.model.MealDetailsUIModel
import com.example.epam_internship_android_molodchenko.presentation.model.MealUIModel

fun List<CategoryEntity>.toCategoryUIModel():
        List<CategoryUIModel> {
    return map {
        CategoryUIModel(
            id = it.id,
            title = it.titleCategory,
            imgCategory = it.imageCategory,
            description = it.descriptionCategory
        )
    }
}

fun List<MealEntity>.toMealUIModel():
        List<MealUIModel> {
    return map {
        MealUIModel(
            id = it.id,
            title = it.titleMeal,
            imgMeal = it.imageMeal
        )
    }
}

fun MealDetailsEntity.toMealDetailsUIModel() =
    MealDetailsUIModel(id, titleMeal, areaMeal, tagMeal, ingredientsMeal, thumbMeal, youtubeUrlMeal)