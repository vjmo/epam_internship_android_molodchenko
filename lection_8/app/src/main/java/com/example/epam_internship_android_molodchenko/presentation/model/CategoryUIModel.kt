package com.example.epam_internship_android_molodchenko.presentation.model

data class CategoryUIModel(
    var id: Int,
    var title: String,
    val imgCategory: String,
    val description: String,
    val activeCategory: Boolean
)