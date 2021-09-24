package com.example.epam_internship_android_molodchenko.data.model.meal

import com.google.gson.annotations.SerializedName

data class ModelMeal(
    @SerializedName("idMeal")
    val idMeal: Int,
    @SerializedName("strMeal")
    val strMeal: String,
    @SerializedName("strMealThumb")
    val strMealThumb: String,
)