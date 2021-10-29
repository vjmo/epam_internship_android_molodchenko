package com.example.epam_internship_android_molodchenko.data.repository

import android.content.SharedPreferences
import com.example.epam_internship_android_molodchenko.data.database.AppDatabase
import com.example.epam_internship_android_molodchenko.data.network.MealApi
import com.example.epam_internship_android_molodchenko.domain.entity.CategoryEntity
import com.example.epam_internship_android_molodchenko.domain.repository.CategoryRepository
import com.example.epam_internship_android_molodchenko.exten_fun.toCategoryEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val api: MealApi,
    private val db: AppDatabase,
    private val sp: SharedPreferences
) : CategoryRepository {

    override fun observeCategory(): Flowable<List<CategoryEntity>> =
        db.getCategoryDao()
            .getCategoryDatabase()
            .map { listItemsCategory -> listItemsCategory.map { it.toCategoryEntity() } }

    override fun requestCategories(): Completable =
        api.getCategories()
            .doOnSuccess {
                if (!sp.contains(KEY_CATEGORY) && it.categoryDbs.isNotEmpty()) {
                    sp
                        .edit()
                        .putInt(KEY_CATEGORY, it.categoryDbs.first().idCategory)
                        .apply()
                }
            }
            .flatMapCompletable {
                db.getCategoryDao().insertCategoryDatabase(it.categoryDbs)
            }

    companion object {
        const val KEY_CATEGORY = "id_category"
    }
}