package com.example.epam_internship_android_molodchenko

import androidx.annotation.DrawableRes

data class ModelCategory(
    val id: Int,
    @DrawableRes val imgCategory: Int,
    val selectCategory: Boolean
)