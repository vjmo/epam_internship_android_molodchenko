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

        val recyclerView = findViewById<RecyclerView>(R.id.rv_one)
        val recyclerViewAdapter = MyAdapter()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerViewAdapter

        recyclerViewAdapter.clickListener
        recyclerViewAdapter.setList(
            listOf(Model("Soy-Glazed Meatloaves with Wasabi Mashed Potatoes & Roasted Carrots", R.drawable.meal_one),
                Model("Steak Diane", R.drawable.meal_two))
        )
    }
}