package com.example.epam_internship_android_molodchenko.data.model.mealDetails

import com.google.gson.annotations.SerializedName

data class MealDetailsListDto(
    @SerializedName("meals")
    val mealsDetails: List<MealDetailsDto>
)