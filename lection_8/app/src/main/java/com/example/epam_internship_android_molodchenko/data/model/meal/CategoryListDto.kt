package com.example.epam_internship_android_molodchenko.data.model.meal

import com.example.epam_internship_android_molodchenko.data.database.model.CategoryDbModel
import com.google.gson.annotations.SerializedName

data class CategoryListDto(
    @SerializedName("categories")
    val categoryDbs: List<CategoryDbModel>
)