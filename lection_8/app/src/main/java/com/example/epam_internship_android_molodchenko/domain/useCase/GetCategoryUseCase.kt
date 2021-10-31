package com.example.epam_internship_android_molodchenko.domain.useCase

import com.example.epam_internship_android_molodchenko.domain.entity.CategoryEntity
import com.example.epam_internship_android_molodchenko.domain.repository.CategoryRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(private val categoryRepository: CategoryRepository) {
    operator fun invoke(): Flowable<List<CategoryEntity>> = categoryRepository.observeCategory()
}