package com.example.epam_internship_android_molodchenko.repository

import com.example.epam_internship_android_molodchenko.api.MealApi
import com.example.epam_internship_android_molodchenko.models.ModelCategoryList
import io.reactivex.Single

class CategoryRepositoryImpl(private val api: MealApi) :CategoryRepository {

    override fun loadCategories(): Single<ModelCategoryList> =
        api.getCategories()
}