package com.example.epam_internship_android_molodchenko.data.repository

import com.example.epam_internship_android_molodchenko.data.network.MealApi
import com.example.epam_internship_android_molodchenko.data.database.AppDatabase
import com.example.epam_internship_android_molodchenko.data.database.model.ModelCategory
import com.example.epam_internship_android_molodchenko.domain.repository.CategoryRepository
import io.reactivex.Completable
import io.reactivex.Flowable

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