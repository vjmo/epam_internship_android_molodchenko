package com.example.epam_internship_android_molodchenko.models

import androidx.annotation.DrawableRes
import com.google.gson.annotations.SerializedName

data class ModelMeal(
    @SerializedName("idMeal")
    val idMeal: Int,
    @SerializedName("strMeal")
    val strMeal: String,
    @SerializedName("strMealThumb")
    val strMealThumb: String,

    val title: String,
    @DrawableRes val imgRes: Int
)