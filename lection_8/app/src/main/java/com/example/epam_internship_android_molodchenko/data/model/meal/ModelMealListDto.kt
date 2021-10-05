package com.example.epam_internship_android_molodchenko.data.model.meal

import com.google.gson.annotations.SerializedName

data class ModelMealListDto(
    @SerializedName("meals")
    val mealDtos: List<ModelMealDto>
)