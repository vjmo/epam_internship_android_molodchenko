package com.example.epam_internship_android_molodchenko

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MealDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_details)
    }
    companion object{
        const val COUNTRY_MEAL = "Japanese"
        const val NAME = "Teriyaki Chicken Casserole"
        fun getIntent(context: Context, name: String, countryMeal: String):Intent{
            val intent = Intent(context, MealDetailsActivity::class.java)
            intent.putExtra(NAME, name)
            intent.putExtra(COUNTRY_MEAL, countryMeal)
            return intent
        }
    }
}