
package com.example.epam_internship_android_molodchenko.data.mapper

import com.example.epam_internship_android_molodchenko.data.database.model.CategoryDbModel
import com.example.epam_internship_android_molodchenko.data.model.meal.CategoryListDto
import com.example.epam_internship_android_molodchenko.data.model.meal.MealDto
import com.example.epam_internship_android_molodchenko.data.model.mealDetails.MealDetailsDto
import com.example.epam_internship_android_molodchenko.domain.entity.CategoryEntity
import com.example.epam_internship_android_molodchenko.domain.entity.MealDetailsEntity
import com.example.epam_internship_android_molodchenko.domain.entity.MealEntity
import java.util.*

fun CategoryDbModel.toCategoryEntity() =
    CategoryEntity(idCategory, nameCategory, strCategoryThumb, strCategoryDescription)

fun List<CategoryDbModel>.toCategoryEntity(): List<CategoryEntity> {
    return map {
        CategoryEntity(
            id = it.idCategory,
            titleCategory = it.nameCategory,
            imageCategory = it.strCategoryThumb,
            descriptionCategory = it.strCategoryDescription
        )
    }
}


fun List<CategoryEntity>.toDbModelCategory(): List<CategoryDbModel> {
    return map {
        CategoryDbModel(
            idCategory = it.id,
            nameCategory = it.titleCategory,
            strCategoryThumb = it.imageCategory,
            strCategoryDescription = it.descriptionCategory
        )
    }
}

fun CategoryListDto.toDbModelCategory(): List<CategoryDbModel> {
    return this.categoryDbs.map {
        CategoryDbModel(
            idCategory = it.idCategory,
            nameCategory = it.nameCategory,
            strCategoryThumb = it.strCategoryThumb,
            strCategoryDescription = it.strCategoryDescription
        )
    }
}

fun CategoryEntity.toDbModelCategory() =
    CategoryDbModel(id, titleCategory, imageCategory, descriptionCategory)

fun MealDto.toMealEntity(): MealEntity {
    return MealEntity(idMeal, strMeal, strMealThumb)
}

fun MealDetailsDto.toMealDetailsEntity(): MealDetailsEntity {
    val ingredientsMapPreview = mapOf(
        strIngredient1 to strMeasure1,
        strIngredient2 to strMeasure2,
        strIngredient3 to strMeasure3,
        strIngredient4 to strMeasure4,
        strIngredient5 to strMeasure5,
        strIngredient6 to strMeasure6,
        strIngredient7 to strMeasure7,
        strIngredient8 to strMeasure8,
        strIngredient9 to strMeasure9,
        strIngredient10 to strMeasure10,
        strIngredient11 to strMeasure11,
        strIngredient12 to strMeasure12,
        strIngredient13 to strMeasure13,
        strIngredient14 to strMeasure14,
        strIngredient15 to strMeasure15,
        strIngredient16 to strMeasure16,
        strIngredient17 to strMeasure17,
        strIngredient18 to strMeasure18,
        strIngredient19 to strMeasure19,
        strIngredient20 to strMeasure20,
    ).filterKeys { !it.isNullOrEmpty() }.toString()

    val removeSurroundingIngr = ingredientsMapPreview.removeSurrounding("{", "}")
    val trimMarginIng = removeSurroundingIngr.trimMargin(",\n")
    val ingredientsMapResult = trimMarginIng.replace('=', ' ', false)

    return MealDetailsEntity(
        id = idMeal,
        titleMeal = strMeal,
        areaMeal = strArea.uppercase(Locale.getDefault()),
        tagMeal = (strTags?.split(",") ?: mutableListOf<String>()) as MutableList<String>,
        ingredientsMeal = ingredientsMapResult,
        thumbMeal = strMealThumb,
        youtubeUrlMeal = strYoutube

    )
}



