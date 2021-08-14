package com.example.epam_internship_android_molodchenko.exten_fun

import com.example.epam_internship_android_molodchenko.models.ModelMealDetails
import com.example.epam_internship_android_molodchenko.uimodel.MealDetailsUIModel
import okhttp3.internal.threadName

fun ModelMealDetails.toMealDetailsUIModel(): MealDetailsUIModel {
    val ingredientsMap = mapOf(
        strIngredient1 to strMeasure1,
        strIngredient2 to strMeasure2,
        strIngredient3 to strMeasure3,
        strIngredient4 to strMeasure4,
        strIngredient5 to strMeasure5,
        strIngredient6 to strMeasure6,
        strIngredient7 to strMeasure7,
        strIngredient8 to strMeasure8,
        strIngredient9 to strMeasure9,
        strIngredient10 to strMeasure10,
        strIngredient11 to strMeasure11,
        strIngredient12 to strMeasure12,
        strIngredient13 to strMeasure13,
        strIngredient14 to strMeasure14,
        strIngredient15 to strMeasure15,
        strIngredient16 to strMeasure16,
        strIngredient17 to strMeasure17,
        strIngredient18 to strMeasure18,
        strIngredient19 to strMeasure19,
        strIngredient20 to strMeasure20,
    ).filterKeys { !it.isNullOrEmpty() }.toString()

    return MealDetailsUIModel(
        nameMealDetails = strMeal,
        area = strArea,
        ingredients = ingredientsMap,
        tags = (strTags?.split(",") ?: mutableListOf<String>()) as MutableList<String>,
        mealThumb = strMealThumb,
        youtube = strYoutube
    )

}

