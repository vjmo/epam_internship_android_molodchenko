package com.example.epam_internship_android_molodchenko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager

class HostMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host_main)
        if (savedInstanceState != null) {
            val fragmentManager: FragmentManager = supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.host_activity, HostFragment()).commit()
        }
    }
}