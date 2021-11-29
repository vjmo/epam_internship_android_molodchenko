package com.example.epam_internship_android_molodchenko.data.repository

import com.example.epam_internship_android_molodchenko.data.database.AppDatabase
import com.example.epam_internship_android_molodchenko.data.network.MealApi
import com.example.epam_internship_android_molodchenko.data.preferences.SharedPreferencesProvider
import com.example.epam_internship_android_molodchenko.domain.entity.CategoryEntity
import com.example.epam_internship_android_molodchenko.domain.repository.CategoryRepository
import com.example.epam_internship_android_molodchenko.data.mapper.toCategoryEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val api: MealApi,
    private val db: AppDatabase,
    private val sp: SharedPreferencesProvider
) : CategoryRepository {

    override fun observeCategory(): Flowable<List<CategoryEntity>> =
        db.getCategoryDao()
            .getCategoryDatabase()
            .map { listItemsCategory -> listItemsCategory.map { it.toCategoryEntity() } }

    override fun requestCategories(): Completable =
        api.getCategories()
            .flatMapCompletable {
                db.getCategoryDao().insertCategoryDatabase(it.categoryDbs)
            }
}