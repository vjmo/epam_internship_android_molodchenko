package com.example.epam_internship_android_molodchenko.repository

import com.example.epam_internship_android_molodchenko.RetrofitInstance
import com.example.epam_internship_android_molodchenko.models.ModelMealDetailsList
import com.example.epam_internship_android_molodchenko.models.ModelMealList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealsDetailsRepository {

    fun loadDetailsData(
        idMeal: Int,
        onSuccess: (ModelMealDetailsList) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        RetrofitInstance.mealApi.getDetails(idMeal).enqueue(
            object : Callback<ModelMealDetailsList> {

                override fun onResponse(
                    call: Call<ModelMealDetailsList>,
                    response: Response<ModelMealDetailsList>
                ) {
                    if (response.isSuccessful) {
                        onSuccess(response.body() ?: ModelMealDetailsList(listOf()))
                    } else onError(Throwable("Error"))
                }

                override fun onFailure(call: Call<ModelMealDetailsList>, t: Throwable) {
                    onError(t)
                }
            }
        )
    }
}