package com.example.epam_internship_android_molodchenko

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MealListActivity : AppCompatActivity() {

    var recyclerView: RecyclerView = RecyclerView(this)

    val res: Resources = resources
    val s1: Array<String> = arrayOf(arrayOf(R.array.meal_string).toString())
    val images: Array<Int> = arrayOf(R.drawable.meal_one, R.drawable.meal_two)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_list)

        recyclerView = findViewById(R.id.recyclerview)

        var myAdapter: Adapter = Adapter(this, s1, images)
    }
}