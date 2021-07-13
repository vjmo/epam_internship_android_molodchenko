package com.example.epam_internship_android_molodchenko.repository

import com.example.epam_internship_android_molodchenko.RetrofitInstance
import com.example.epam_internship_android_molodchenko.models.ModelCategoryList
import com.example.epam_internship_android_molodchenko.models.ModelMealDetailsList
import com.example.epam_internship_android_molodchenko.models.ModelMealList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRepository /*{
    fun loadCategories(
        onSuccess: (ModelCategoryList) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val categoriesData = RetrofitInstance.mealApi
            .getCategories()
            .enqueue(
                object : Callback<ModelCategoryList> {
                    override fun onResponse(
                        call: Call<ModelCategoryList>,
                        response: Response<ModelCategoryList>
                    ) {
                        if (response.isSuccessful) {
                            onSuccess(response.body()?: ModelCategoryList(listOf()))
                        }
                        else onError(Throwable("error"))
                    }

                    override fun onFailure(call: Call<ModelCategoryList>, t: Throwable) {
                        onError(t)
                    }
                }
            )
    }
}*/