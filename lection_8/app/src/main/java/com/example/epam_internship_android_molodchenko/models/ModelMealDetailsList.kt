package com.example.epam_internship_android_molodchenko.models

import com.google.gson.annotations.SerializedName

data class ModelMealDetailsList(
    @SerializedName("meals")
    val mealsDetails: List<ModelMealDetails>
)