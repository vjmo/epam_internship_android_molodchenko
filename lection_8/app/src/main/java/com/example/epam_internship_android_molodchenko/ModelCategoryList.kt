package com.example.epam_internship_android_molodchenko

import com.example.epam_internship_android_molodchenko.models.ModelCategory
import com.google.gson.annotations.SerializedName

data class ModelCategoryList(
    @SerializedName("categories")
    val categories: List<ModelCategory>
)