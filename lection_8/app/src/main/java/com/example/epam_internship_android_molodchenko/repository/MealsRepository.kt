package com.example.epam_internship_android_molodchenko.repository

import com.example.epam_internship_android_molodchenko.RetrofitInstance
import com.example.epam_internship_android_molodchenko.models.ModelMealList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealsRepository/* {
    fun initMealsData(strCategory: String) {
        val mealsData = RetrofitInstance.mealApi
            .getMeals(strCategory)
            .enqueue(
                object : Callback<ModelMealList> {
                    override fun onResponse(
                        call: Call<ModelMealList>,
                        response: Response<ModelMealList>
                    ) {
                        val meals: ModelMealList? = response.body()
                    }

                    override fun onFailure(call: Call<ModelMealList>, t: Throwable) {
                        val errorMessage = t.message
                    }
                }
            )
    }
}*/