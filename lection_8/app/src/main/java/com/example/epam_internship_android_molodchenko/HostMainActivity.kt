package com.example.epam_internship_android_molodchenko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HostMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host_main)
        if (savedInstanceState != null) {
            val fragmentManager: FragmentManager = supportFragmentManager
            fragmentManager
                .beginTransaction()
                .replace(R.id.host_activity, HostFragment())
                .commit()
        }

        val recyclerViewMeal = findViewById<RecyclerView>(R.id.rv_one)
        val recyclerViewAdapterMeal = MealAdapter()

        recyclerViewMeal.layoutManager = LinearLayoutManager(this)
        recyclerViewMeal.adapter = recyclerViewAdapterMeal

        recyclerViewAdapterMeal.clickListener
        recyclerViewAdapterMeal.setList(
            listOf(
                ModelMeal(
                    "Soy-Glazed Meatloaves with Wasabi Mashed Potatoes & Roasted Carrots",
                    R.drawable.meal_one
                ),
                ModelMeal("Steak Diane", R.drawable.meal_two)
            )
        )

        val recyclerViewCategory = findViewById<RecyclerView>(R.id.rv_category)
        val recyclerViewAdapterCategory = CategoryAdapter()

        recyclerViewCategory.layoutManager = LinearLayoutManager(this)
        recyclerViewCategory.adapter = recyclerViewAdapterCategory

        recyclerViewAdapterCategory.clickListener
        recyclerViewAdapterCategory.setList(
            listOf(
                ModelCategory
                    (1, R.drawable.tb_one, true),
                ModelCategory
                    (2, R.drawable.tb_two, false),
                ModelCategory
                    (3, R.drawable.tb_three, false),
                ModelCategory
                    (4, R.drawable.tb_four, false),
                ModelCategory
                    (5, R.drawable.tb_five, false)
            )
        )
    }
}