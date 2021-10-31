package com.example.epam_internship_android_molodchenko.domain.repository

import com.example.epam_internship_android_molodchenko.domain.entity.CategoryEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface CategoryRepository {//bind module

    fun requestCategories(): Completable

    fun observeCategory(): Flowable<List<CategoryEntity>>

}