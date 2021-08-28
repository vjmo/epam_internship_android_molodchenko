package com.example.epam_internship_android_molodchenko.models

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "category_data")
data class ModelCategory(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("idCategory")
    val idCategory: Int,
    @ColumnInfo(name = "name")
    @SerializedName("strCategory")
    val nameCategory: String,
    @ColumnInfo(name = "thumb")
    @SerializedName("strCategoryThumb")
    val strCategoryThumb: String,
    @ColumnInfo(name = "description")
    @SerializedName("strCategoryDescription")
    val strCategoryDescription: String,
    @ColumnInfo(name = "active")
    var activeCategory: Boolean = false
)