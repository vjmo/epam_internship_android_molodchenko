package com.example.epam_internship_android_molodchenko

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MealListActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_list)

        val imageView: ImageView = findViewById(R.id.meal_one_img)
        val textView: TextView = findViewById(R.id.txt_itm_meal_one)
        val mealOne = Meal(
            1, "Teriyaki Chicken Casserole", "JAPANESE",
            " ", " Soy sauce - 3/4 cup\n" +
                    "        Water - 1/2 cup\n" +
                    "        Brown sugar - 1/4 cup\n" +
                    "        Ground ginger - 1/2 teaspoon\n" +
                    "        Minced garlic - 1/2 teaspoon\n" +
                    "        Cornstarch - 4 tablespoons\n" +
                    "        Chicken breasts - 2\n" +
                    "        Stir-fry vegetables - 1 (12 oz.)\n" +
                    "        Brown rice - 3 cups"
        )

        val itemMealList: List<Meal> = listOf(mealOne)

        textView.setOnClickListener {
            startActivity(MealDetailsActivity.getIntent(this, mealOne.name, mealOne.country))
        }
        imageView.setOnClickListener {
            startActivity(MealDetailsActivity.getIntent(this, mealOne.name, mealOne.country))
        }

    }

}