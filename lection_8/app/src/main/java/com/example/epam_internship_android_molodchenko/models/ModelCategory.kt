package com.example.epam_internship_android_molodchenko.models

import androidx.annotation.DrawableRes
import com.google.gson.annotations.SerializedName

data class ModelCategory(
    @SerializedName("idCategory")
    val idCategory: Int,
    @SerializedName("strCategory")
    val nameCategory: String,
    @SerializedName("strCategoryThumb")
    val strCategoryThumb: String,
    @SerializedName("strCategoryDescription")
    val strCategoryDescription: String,

    var selectCategory: Boolean
)