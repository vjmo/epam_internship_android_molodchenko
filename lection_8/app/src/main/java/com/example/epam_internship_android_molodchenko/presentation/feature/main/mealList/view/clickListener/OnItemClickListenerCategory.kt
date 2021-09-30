package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view.clickListener

import com.example.epam_internship_android_molodchenko.data.database.model.DbModelCategory
import com.example.epam_internship_android_molodchenko.presentation.model.CategoryUIModel

interface OnItemClickListenerCategory {
    fun onItemClick(categoryUI: CategoryUIModel)
}
// создать клик, булево знач(параметры), а  ретерн - юнит