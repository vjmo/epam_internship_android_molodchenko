package com.example.epam_internship_android_molodchenko.di.module

import com.example.epam_internship_android_molodchenko.data.network.MealApi
import com.example.epam_internship_android_molodchenko.data.network.RetrofitInstance
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
class MealApiModule {

    @Provides
    fun provideMealApi(): MealApi = Retrofit.Builder()
        .baseUrl(RetrofitInstance.BASE_URL)
        .client(RetrofitInstance.client)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MealApi::class.java)
}