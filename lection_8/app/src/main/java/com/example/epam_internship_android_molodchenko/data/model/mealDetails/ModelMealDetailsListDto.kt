package com.example.epam_internship_android_molodchenko.data.model.mealDetails

import com.google.gson.annotations.SerializedName

data class ModelMealDetailsListDto(
    @SerializedName("mealDtos")
    val mealsDetails: MutableList<ModelMealDetailsDto>
)