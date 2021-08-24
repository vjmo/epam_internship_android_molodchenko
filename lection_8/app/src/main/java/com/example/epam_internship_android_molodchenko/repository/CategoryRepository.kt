package com.example.epam_internship_android_molodchenko.repository

import com.example.epam_internship_android_molodchenko.models.ModelCategoryList
import io.reactivex.Single

interface CategoryRepository {

    fun loadCategories(): Single<ModelCategoryList>

}