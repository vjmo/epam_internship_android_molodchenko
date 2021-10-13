package com.example.epam_internship_android_molodchenko.presentation.feature.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import com.example.epam_internship_android_molodchenko.R

class HostMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host_main)
    /*  val navHostFragment = supportFragmentManager.findFragmentById(R.id.host_activity) as NavHostFragment
        val navController = navHostFragment.navController
        /*fragmentManager
            .beginTransaction()
            .replace(R.id.host_activity, HostFragment.newInstance())
            .commit()*/*/
    }
}