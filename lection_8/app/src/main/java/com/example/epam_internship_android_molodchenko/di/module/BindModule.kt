package com.example.epam_internship_android_molodchenko.di.module

import com.example.epam_internship_android_molodchenko.data.repository.CategoryRepositoryImpl
import com.example.epam_internship_android_molodchenko.data.repository.MealsDetailsRepositoryImpl
import com.example.epam_internship_android_molodchenko.data.repository.MealsRepositoryImpl
import com.example.epam_internship_android_molodchenko.domain.repository.CategoryRepository
import com.example.epam_internship_android_molodchenko.domain.repository.MealsDetailsRepository
import com.example.epam_internship_android_molodchenko.domain.repository.MealsRepository
import dagger.Binds
import dagger.Module

@Module
interface BindModule {

    @Binds
    fun bindCategoryRepository(categoryRepository: CategoryRepositoryImpl): CategoryRepository

    @Binds
    fun bindMealsRepository(mealsRepository: MealsRepositoryImpl): MealsRepository

    @Binds
    fun bindMealsDetails(mealsDetailsRepository: MealsDetailsRepositoryImpl): MealsDetailsRepository
}