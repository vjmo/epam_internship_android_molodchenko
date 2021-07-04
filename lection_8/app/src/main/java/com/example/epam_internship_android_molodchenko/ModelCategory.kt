package com.example.epam_internship_android_molodchenko

import androidx.annotation.DrawableRes
import com.google.gson.annotations.SerializedName

data class ModelCategory(
    @SerializedName("idCategory")
    val idCategory: Int,
    @SerializedName("strCategory")
    val strCategory: String,
    @SerializedName("strCategoryThumb")
    val strCategoryThumb: String,
    @SerializedName("strCategoryDescription")
    val strCategoryDescription: String,
    //
    @DrawableRes
    val imgCategory: Int,
    val selectCategory: Boolean
)