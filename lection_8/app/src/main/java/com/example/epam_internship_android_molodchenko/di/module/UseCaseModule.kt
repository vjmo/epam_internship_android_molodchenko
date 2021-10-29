package com.example.epam_internship_android_molodchenko.di.module

import com.example.epam_internship_android_molodchenko.domain.repository.CategoryRepository
import com.example.epam_internship_android_molodchenko.domain.repository.MealsDetailsRepository
import com.example.epam_internship_android_molodchenko.domain.repository.MealsRepository
import com.example.epam_internship_android_molodchenko.domain.useCase.GetCategoryUseCase
import com.example.epam_internship_android_molodchenko.domain.useCase.GetMealDetailsUseCase
import com.example.epam_internship_android_molodchenko.domain.useCase.GetMealListUseCase
import com.example.epam_internship_android_molodchenko.domain.useCase.RequestCategoryUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetCategoryUseCase(categoryRepository: CategoryRepository) =
        GetCategoryUseCase(categoryRepository)

    @Provides
    fun provideRequestCategoryUseCase(categoryRepository: CategoryRepository) =
        RequestCategoryUseCase(categoryRepository)

    @Provides
    fun provideGetMealListUseCase(mealsRepository: MealsRepository) =
        GetMealListUseCase(mealsRepository)

    @Provides
    fun provideGetMealDetailsUseCase(mealsDetailsRepository: MealsDetailsRepository) =
        GetMealDetailsUseCase(mealsDetailsRepository)
}