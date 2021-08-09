package com.example.epam_internship_android_molodchenko.repository

import com.example.epam_internship_android_molodchenko.RetrofitInstance
import com.example.epam_internship_android_molodchenko.models.ModelCategoryList
import com.example.epam_internship_android_molodchenko.models.ModelMealList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealsRepository {

    fun loadMealsData(
        strCategory: String,
        onSuccess: (ModelMealList) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        RetrofitInstance.mealApi.getMeals(strCategory).enqueue(
            object : Callback<ModelMealList> {

                override fun onResponse(
                    call: Call<ModelMealList>,
                    response: Response<ModelMealList>
                ) {
                    if (response.isSuccessful) {
                        onSuccess(response.body() ?: ModelMealList(mutableListOf()))
                    } else onError(Throwable("Error"))
                }

                override fun onFailure(call: Call<ModelMealList>, t: Throwable) {
                    onError(t)
                }
            }
        )
    }
}