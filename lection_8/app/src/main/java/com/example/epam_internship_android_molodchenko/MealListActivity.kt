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

        val intent = Intent(this, MealDetailsActivity::class.java)

        imageView.setOnClickListener {
            startActivity(intent)
        }
        textView.setOnClickListener {
            startActivity(intent)
        }
    }

}