package com.example.epam_internship_android_molodchenko.domain.useCase

import com.example.epam_internship_android_molodchenko.domain.repository.CategoryRepository
import io.reactivex.Completable

class RequestCategoryUseCase(private val categoryRepository: CategoryRepository) {
    operator fun invoke(): Completable = categoryRepository.requestCategories()
}