package com.example.epam_internship_android_molodchenko.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    internal const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    internal val logging = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    internal val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
}