package com.example.epam_internship_android_molodchenko.models

import com.google.gson.annotations.SerializedName

data class ModelMealList(
    @SerializedName("meals")
    val meals: MutableList<ModelMeal>
)