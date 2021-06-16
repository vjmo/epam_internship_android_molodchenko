package com.example.epam_internship_android_molodchenko

import android.accessibilityservice.GestureDescription
import java.io.FileDescriptor

data class Meal (
    val id: Int,
    val name: String,
    val country: String,
    val category: String,
    val description: String
)