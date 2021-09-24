package com.example.epam_internship_android_molodchenko.data.model.meal

import com.google.gson.annotations.SerializedName

data class ModelMealList(
    @SerializedName("meals")
    val meals: MutableList<ModelMeal>
)