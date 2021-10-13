package com.example.epam_internship_android_molodchenko.presentation.feature.main.view

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.epam_internship_android_molodchenko.R
import com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view.MealListFragment

class HostFragment : Fragment((R.layout.fragment_host)) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_host, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       /* parentFragmentManager
            .beginTransaction()
            .replace(R.id.host_fragment, MealListFragment.newInstance())
            .commit()*/
    }

    companion object {
        fun newInstance() = HostFragment()
    }
}