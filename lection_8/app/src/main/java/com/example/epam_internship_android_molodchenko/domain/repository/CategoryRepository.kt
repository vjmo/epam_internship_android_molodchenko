package com.example.epam_internship_android_molodchenko.domain.repository

import com.example.epam_internship_android_molodchenko.data.database.model.ModelCategory
import io.reactivex.Completable
import io.reactivex.Flowable

interface CategoryRepository {

    fun requestCategories(): Completable

    fun observeCategory(): Flowable<List<ModelCategory>>

}