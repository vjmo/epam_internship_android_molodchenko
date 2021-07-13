package com.example.epam_internship_android_molodchenko.api


import com.example.epam_internship_android_molodchenko.models.ModelCategory
import com.example.epam_internship_android_molodchenko.models.ModelCategoryList
import com.example.epam_internship_android_molodchenko.models.ModelMealDetailsList
import com.example.epam_internship_android_molodchenko.models.ModelMealList
import com.example.epam_internship_android_molodchenko.repository.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("categories.php")
    fun getCategories(): Call<MutableList<ModelCategory>>

    companion object{
       fun create(): MealApi {
           val retrofit =  Retrofit.Builder()
               .baseUrl(Constants.BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .build()
           return  retrofit.create(MealApi::class.java)
       }
   }

    @GET("filter.php")
    fun getMeals(@Query("c") strCategory: String): Call<ModelMealList>

    @GET("lookup.php")
    fun getDetails(@Query("i") idMeal: Int): Call<ModelMealDetailsList>
}