package com.example.epam_internship_android_molodchenko.repository

import com.example.epam_internship_android_molodchenko.RetrofitInstance
import com.example.epam_internship_android_molodchenko.models.ModelMealDetailsList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealsDetailsRepository /*{
    fun initDetailsData(idMeal: Int) {
        val detailsData = RetrofitInstance.mealApi
            .getDetails(idMeal)
            .enqueue(
                object : Callback<ModelMealDetailsList> {
                    override fun onResponse(
                        call: Call<ModelMealDetailsList>,
                        response: Response<ModelMealDetailsList>
                    ) {
                        val details: ModelMealDetailsList? = response.body()
                    }

                    override fun onFailure(call: Call<ModelMealDetailsList>, t: Throwable) {
                        val errorMessage = t.message
                    }
                }
            )
    }
}*/