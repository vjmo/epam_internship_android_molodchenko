package com.example.epam_internship_android_molodchenko.repository

import com.example.epam_internship_android_molodchenko.api.MealApi
import com.example.epam_internship_android_molodchenko.data.AppDatabase
import com.example.epam_internship_android_molodchenko.models.ModelCategory
import com.example.epam_internship_android_molodchenko.models.ModelCategoryList
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class CategoryRepositoryImpl(
    private val api: MealApi,
    private val db: AppDatabase
) : CategoryRepository {

    override fun observeCategory(): Flowable<List<ModelCategory>> =
        db.getCategoryDao()
            .getCategoryDatabase()

    override fun requestCategories(): Completable =
        api.getCategories()
            .flatMapCompletable {
                db.getCategoryDao().insertCategoryDatabase(it.categories)
            }

}