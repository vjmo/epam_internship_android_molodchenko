package com.example.epam_internship_android_molodchenko.data.model.meal

import com.example.epam_internship_android_molodchenko.data.database.model.DbModelCategory
import com.google.gson.annotations.SerializedName

data class ModelCategoryListDto(
    @SerializedName("categories")
    val categoryDbs: List<DbModelCategory>
)