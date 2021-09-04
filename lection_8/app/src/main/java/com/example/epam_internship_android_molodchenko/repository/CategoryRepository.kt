package com.example.epam_internship_android_molodchenko.repository

import com.example.epam_internship_android_molodchenko.models.ModelCategory
import com.example.epam_internship_android_molodchenko.models.ModelCategoryList
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface CategoryRepository {

    fun requestCategories(): Completable

    fun observeCategory(): Flowable<List<ModelCategory>>

}