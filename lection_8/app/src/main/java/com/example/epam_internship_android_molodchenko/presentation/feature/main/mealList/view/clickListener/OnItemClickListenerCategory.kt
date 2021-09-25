package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view.clickListener

import com.example.epam_internship_android_molodchenko.data.database.model.DbModelCategory

interface OnItemClickListenerCategory {
    fun onItemClick(categoryDb: DbModelCategory)
}
// создать клик, булево знач(параметры), а  ретерн - юнит