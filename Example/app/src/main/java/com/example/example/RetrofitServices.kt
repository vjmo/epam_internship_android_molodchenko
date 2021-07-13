package com.example.example

import retrofit2.Call
import retrofit2.http.*

interface RetrofitServices {
    @GET("marvel")
    fun getMovieList(): Call<MutableList<Movie>>
}