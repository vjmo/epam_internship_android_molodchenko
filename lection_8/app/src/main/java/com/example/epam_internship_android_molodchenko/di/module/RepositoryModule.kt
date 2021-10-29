package com.example.epam_internship_android_molodchenko.di.module

import android.content.SharedPreferences
import com.example.epam_internship_android_molodchenko.data.database.AppDatabase
import com.example.epam_internship_android_molodchenko.data.network.MealApi
import com.example.epam_internship_android_molodchenko.data.repository.CategoryRepositoryImpl
import com.example.epam_internship_android_molodchenko.data.repository.MealsDetailsRepositoryImpl
import com.example.epam_internship_android_molodchenko.data.repository.MealsRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    //db or dao
    @Provides
    fun provideCategoryRepositoryImpl(
        mealApi: MealApi,
        appDatabase: AppDatabase,
        sharedPreferences: SharedPreferences
    ) =
        CategoryRepositoryImpl(mealApi, appDatabase, sharedPreferences)

    @Provides
    fun provideMealsRepositoryImpl(mealApi: MealApi) = MealsRepositoryImpl(mealApi)

    @Provides
    fun provideMealsDetailsRepositoryImpl(mealApi: MealApi) = MealsDetailsRepositoryImpl(mealApi)
}