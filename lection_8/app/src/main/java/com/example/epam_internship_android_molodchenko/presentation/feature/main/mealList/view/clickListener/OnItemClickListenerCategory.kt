package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view.clickListener

import com.example.epam_internship_android_molodchenko.data.database.model.ModelCategory

interface OnItemClickListenerCategory {
    fun onItemClick(category: ModelCategory)
}
// создать клик, булево знач(параметры), а  ретерн - юнит